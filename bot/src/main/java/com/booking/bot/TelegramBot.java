package com.booking.bot;

import com.booking.bot.adapter.BookingAdapter;
import com.booking.bot.dto.OrganizationDto;
import com.booking.bot.dto.PersonDto;
import com.booking.bot.service.BookingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
@NoArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private Map<Long, String> statusChat = new HashMap<>();

    private BookingAdapter bookingAdapter;
    private BookingService bookingService;
    private OrganizationDto organizationDto;
    private PersonDto personDto;

    @Autowired
    public TelegramBot(BookingAdapter bookingAdapter, BookingService bookingService) {
        this.bookingAdapter = bookingAdapter;
        this.bookingService = bookingService;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) {
        String[] param = callbackQuery.getData().split(":");
        if (param[0].contains("Res")) {
//            Reservation reservationForBooking = bookingService.createReservationDtoForBooking(Long.valueOf(param[1]),
//                    Integer.parseInt(param[2]), Integer.parseInt(param[3]), Integer.parseInt(param[4]));

//            BookingDto bookingDto = bookingService.createBookingForSending(personDto, organizationDto, reservationForBooking);
            OrganizationDto changedOrganization = bookingService.setChangedReservationToOrganization(organizationDto, param[1]);

//            bookingAdapter.updateOrganizationWithNewReservation(changedOrganization, "/organization");
//            bookingAdapter.addBooking(bookingDto, "/bookings");
        }
        if (param[0].contains("Org")) {
            organizationDto = bookingAdapter.getOrganization("/organization?name={name}", param[1]);
        }
    }

    private void handleMessage(Message message) throws TelegramApiException, JsonProcessingException {

        PersonDto personDto = bookingAdapter.searchOfPerson("/person?id={id}", message.getFrom().getId());
        System.out.println(personDto);

        if ((personDto == null || personDto.id().equals(message.getFrom().getId()))
                && statusChat.isEmpty()) {
            statusChat.put(message.getFrom().getId(), "/sign_up");
            executeString("Sign up, please: /sign_up", message);
            return;
        } else if (personDto != null && personDto.id().equals(message.getFrom().getId())) {
            executeString("Hello, " + personDto.name(), message);
        }
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command =
                        message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());

                switch (command) {
                    case "/sign_up" -> {
                        statusChat.put(message.getFrom().getId(), "/sign_up");
                        executeString("Type your name, please", message);
                        return;
                    }
                    case "/find" -> {
                        statusChat.put(message.getFrom().getId(), "/find");
                        executeString("Введите имя организации", message);
                    }
                    case "/organizations" -> {
                        statusChat.put(message.getFrom().getId(), "/organizations");
                        executeString("Список организаций: ", message);
                    }
                    case "/create_booking" -> {
                        statusChat.put(message.getFrom().getId(), "/create_booking");
                        createButtonsForOrganization(message);
                        return;
                    }
                    case "/create_reserve" -> {
                        statusChat.put(message.getFrom().getId(), "/create_reserve");
                        createButtonsForReservations(message, organizationDto);
                        System.out.println("switch " + organizationDto);
                        return;
                    }
                    default -> executeString("Сервис по бронированию.\n" +
                            "/find - поиск бронирования.\n" +
                            "/organizations - просмотр доступных организаций.", message);
                }
            }
        }

        String messageText = message.getText();
        Optional<String> messageString = parseString(messageText);
        String mapValue = statusChat.get(message.getFrom().getId());

        switch (mapValue) {
            case "/sign_up" -> {
                bookingAdapter.addPerson(new PersonDto(message.getFrom().getId(), messageString.get()), "/person");
                statusChat.put(message.getFrom().getId(), "free");
                executeString("Sign up is done. \n" +
                        "Сервис по бронированию.\n" +
                        "/find - поиск бронирования.\n" +
                        "/organizations - просмотр доступных организаций.", message);
                return;
            }

            case "/find" -> {
                OrganizationDto org = bookingAdapter.getOrganization("/organization?name={name}", messageString.get());
                String stringMessage = "Название: " + org.name() + "\n" +
                        "Расписание: " + org.schedule() + "\n" +
                        "Средний чек: " + org.averageCheck() + "\n" +
                        "Рейтинг: " + org.rating() + "\n";
                executeString(stringMessage, message);
                statusChat.put(message.getFrom().getId(), "free");
                return;
            }

            case "/organizations" -> {
                List<OrganizationDto> organizationDto
                        = bookingAdapter.getOrganizations("organization?pageNo=0&pageSize=2&sortBy=name");
                System.out.println(organizationDto);
                StringBuilder stringBuilder = new StringBuilder();
                for (OrganizationDto o : organizationDto) {
                    stringBuilder.append(o.name()).append("\n");
                    System.out.println(o);
                }

                executeString(stringBuilder.toString(), message);
                statusChat.put(message.getFrom().getId(), "free");
            }
            default -> {
                executeString("Сервис по бронированию.\n" +
                        "/hello - поздороваться \n" +
                        "/find - поиск бронирования.\n" +
                        "/organizations - просмотр доступных организаций.", message);
            }
        }
    }
//        if (statusChat.get(message.getFrom().getId()).equals("/organizations")) {
//            OrganizationDto[] organizationDto = bookingAdapter.getArrayOrganizations("organization?pageNo=0&pageSize=2&sortBy=name");
//            StringBuilder stringBuilder = new StringBuilder();
//            for (OrganizationDto o : organizationDto) {
//                stringBuilder.append(o.name()).append("\n");
//            }
//
//            executeString(stringBuilder.toString(), message);
//            statusChat.put(message.getFrom().getId(), "free");
//        } else {


    private void executeString(String executeStr, Message message) throws TelegramApiException {
        execute(
                SendMessage.builder()
                        .text(executeStr)
                        .chatId(message.getChatId().toString())
                        .build()
        );
    }

    private void createButtonsForReservations(Message message, OrganizationDto organizationDto) throws TelegramApiException {

//        System.out.println(organizationDto.id());
//        List<Reservation> reservations
//                = bookingAdapter.getReservations("/reservation?organizationId={organizationId}", organizationDto.id());
//        System.out.println(reservations);
////        List<Reservation> reservationDtos = organizationDto.reservationsList();
//        List<List<InlineKeyboardButton>> buttonsForReservation = new ArrayList<>();
//        reservations.removeIf(reservation -> reservation.getNumbersOfTables() <= 0);
//        personDto = new PersonDto(message.getFrom().getId(), message.getFrom().getUserName());
//        for (Reservation reservation : reservations) {
//            buttonsForReservation.add(
//                    Arrays.asList(
//                            InlineKeyboardButton.builder()
//                                    .text("Доступное время: " + reservation.getBeginning() + "-"
//                                            + reservation.getEnding() + " Количество свободных столов: " + reservation.getNumbersOfTables())
//                                    .callbackData("Reservation:" + reservation.getId() + ":"
//                                            + reservation.getBeginning() + ":"
//                                            + reservation.getEnding() + ":"
//                                            + reservation.getNumbersOfTables())
//                                    .build()));
//        }
//        execute(
//                SendMessage.builder()
//                        .text("Please choose product category")
//                        .chatId(message.getChatId().toString())
//                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttonsForReservation).build())
//                        .build());
    }


    private void createButtonsForOrganization(Message message) throws TelegramApiException {
        List<List<InlineKeyboardButton>> buttonsForOrganization = new ArrayList<>();
        List<OrganizationDto> organizationDto = bookingAdapter.getOrganizations("/organization");
        if (organizationDto.size() == 0) {
            executeString("Organizations not found", message);
            return;
        }
        for (OrganizationDto organizationDtoTemp : organizationDto) {
            buttonsForOrganization.add(
                    Arrays.asList(
                            InlineKeyboardButton.builder()
                                    .text(organizationDtoTemp.name())
                                    .callbackData("Organization:" + organizationDtoTemp.name())
                                    .build()));
        }

        executeString("Please choose product organization", message);
        executeString("Выберите время: /create_reserve", message);
        return;
    }

    private Optional<String> parseString(String messageText) {
        try {
            return Optional.of(String.valueOf(messageText));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
