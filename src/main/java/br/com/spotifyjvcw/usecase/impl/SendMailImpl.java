package br.com.spotifyjvcw.usecase.impl;

import br.com.spotifyjvcw.domain.TermSearch;
import br.com.spotifyjvcw.domain.contract.Position;
import br.com.spotifyjvcw.gateway.MailSenderGateway;
import br.com.spotifyjvcw.usecase.BuildPosition;
import br.com.spotifyjvcw.usecase.SendMail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static br.com.spotifyjvcw.utils.PositionUtils.generateHtml;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Component
@RequiredArgsConstructor
public class SendMailImpl implements SendMail {

    @Value("${mail-configuration.defaultMailSender}")
    private String defaulMailSender;
    private String emailToSend;
    private final MailSenderGateway mailSenderGateway;
    private final BuildPosition buildPosition;
    private static final String SUBJECT_ARTIST_RANKING = "Mudança ranking de artistas dia %s | %s";
    private static final String SUBJECT_TRACK_RANKING = "Mudança ranking de músicas dia %s | %s";
    private static final DateTimeFormatter BRAZIL_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void whenChangePositions(String clientId, String sendEmail) {
        emailToSend = isNotBlank(sendEmail) ? sendEmail : defaulMailSender;

        LocalDate now = LocalDate.now();
        sendMailArtist(clientId, TermSearch.SHORT, now);
        sendMailArtist(clientId, TermSearch.MEDIUM, now);
        sendMailArtist(clientId, TermSearch.LONG, now);

        sendMailTrack(clientId, TermSearch.SHORT, now);
        sendMailTrack(clientId, TermSearch.MEDIUM, now);
        sendMailTrack(clientId, TermSearch.LONG, now);
    }

    private void sendMailArtist(String clientId, TermSearch termSearch, LocalDate date) {
        sendPosition(buildPosition.buildArtist(termSearch, clientId, date), String.format(SUBJECT_ARTIST_RANKING, date.format(BRAZIL_DATE_FORMATTER), termSearch.name()), termSearch);
    }

    private void sendMailTrack(String clientId, TermSearch termSearch, LocalDate date) {
        sendPosition(buildPosition.buildTrack(termSearch, clientId, date), String.format(SUBJECT_TRACK_RANKING, date.format(BRAZIL_DATE_FORMATTER), termSearch.name()), termSearch);
    }

    private void sendPosition(List<Position> positionList, String subject, TermSearch termSearch) {
        if (positionList.stream().filter(x -> x.isPositionChanged() != 0).count() > termSearch.getMinPosition())
            mailSenderGateway.execute(emailToSend, generateHtml(positionList), subject);
    }
}
