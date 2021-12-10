package br.com.technologies.venom.medalertapp.utils;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static br.com.technologies.venom.medalertapp.utils.Constantes.TAG;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.technologies.venom.medalertapp.services.AlarmReceiver;

public class Rotinas {
    private static AlarmManager alarmManager;
    private static PendingIntent pendingIntent;
    private static int INTERVALO_ALARME = 60 * 1000;

    //Formato de exibição de data em formato comum
    public static SimpleDateFormat formatarDataHumano = new SimpleDateFormat("dd/MM/yyyy");

    //Formato de exibição de hora em formato comum
    public static SimpleDateFormat formatarHoraHumano = new SimpleDateFormat("HH:mm");

    /**
     * Trocar de uma tela para outra
     *
     * @param fragment
     * @param tela
     */
    public static void trocarTela(Fragment fragment, int tela) {
        try {
            NavHostFragment.findNavController(fragment).navigate(tela);
        } catch (Exception e) {
            Log.e(TAG, "trocarTela: Ocorreu um erro ao tentar trocar para a tela", e);
        }
    }

    /**
     * Trocar de uma tela para outra, passando um bundle (extras)
     *
     * @param fragment
     * @param tela
     * @param bundle
     */
    public static void trocarTelaComExtra(Fragment fragment, int tela, Bundle bundle) {
        try {
            NavHostFragment.findNavController(fragment).navigate(tela, bundle);
        } catch (Exception e) {
            Log.e(TAG, "trocarTelaComExtra: Ocorreu um erro ao tentar trocar para a tela com extras", e);
        }
    }

    public static void criarCanalNotificacao(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "medalertReminderChannel";
            String description = "MedAlert";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("medalert", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void configurarAlarme(Context context, Calendar calendar) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, 0);

//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), INTERVALO_ALARME, pendingIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        Log.d(TAG, "configurarAlarme: Alarme configurar para " + calendar.getTime().toString());
        Toast.makeText(context.getApplicationContext(), "Alarme configurado com sucesso", Toast.LENGTH_SHORT).show();
    }

    public static void cancelarAlarme(Context context){
        Intent intent = new Intent(context.getApplicationContext(), AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(context.getApplicationContext(), 0, intent, 0);

        if (alarmManager == null) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(context.getApplicationContext(), "Alarme cancelado", Toast.LENGTH_SHORT).show();
    }

    public static void configurarHoraMedicamento(String data, String hora, Context context) {
        DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
        Date dataConvertida = new Date();
        int dia = 0, mes = 0, ano = 0;

        try{
            dataConvertida = dataFormatada.parse(data);
            dia = dataConvertida.getDate();
            mes = dataConvertida.getMonth();
            ano = dataConvertida.getYear();

        }catch (Exception e){
            Log.e(TAG, "configurarHoraMedicamento: Ocorreu um erro ao tentar converter a data informada", e);
        }

        String[] horaQuebrada = hora.split(":"); //0 = Hora, 1 = Minuto

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, dia);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaQuebrada[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(horaQuebrada[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        criarCanalNotificacao(context);
        configurarAlarme(context, calendar);
    }
}
