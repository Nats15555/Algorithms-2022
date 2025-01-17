package lesson1;

import kotlin.NotImplementedError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    //Время O(n)
    //Память О(n)
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        String strBuff;
        try (BufferedReader in = new BufferedReader(new FileReader(inputName));
             FileWriter writer = new FileWriter(outputName)) {

            int[] neg = new int[2731];
            int[] pos = new int[5001];

            while ((strBuff = in.readLine()) != null) {
                if (!strBuff.matches("^[+-]?([0-9]*[.])?[0-9]+$")) {
                    throw new IllegalArgumentException();
                }
                float temp = Float.parseFloat(strBuff);
                if (temp >= -273.0 && temp <= 500.0) {
                    temp *= 10;
                    if (temp >= 0) {
                        pos[(int) temp]++;
                    } else {
                        neg[(int) Math.abs(temp)]++;
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            for (int i = 2730; i > 0; i--) {
                int buff = neg[i];
                while (buff > 0) {
                    writer.write("-" + (double) i / 10 +"\n");
                    buff--;
                }
            }

            for (int i = 0; i <= 5000; i++) {
                int buff = pos[i];
                while (buff > 0) {
                    writer.write((double) i / 10 + "\n");
                    buff--;
                }
            }
            writer.flush();
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    //Память О(n)
    //Время O(n)
    static public void sortSequence(String inputName, String outputName) throws IOException {
        String strBuff = "";
        List<Integer> listSequence = new ArrayList<>();
        Map<Integer, Integer> mapSequence = new HashMap<>();
        try (BufferedReader in = new BufferedReader(new FileReader(inputName));
             FileWriter writer = new FileWriter(outputName)) {
            while ((strBuff = in.readLine()) != null) {
                if (!strBuff.matches("^\\d+$")) {
                    throw new IllegalArgumentException();
                }
                listSequence.add(Integer.parseInt(strBuff));

            }
            for (Integer it : listSequence) {
                if (mapSequence.containsKey(it)) {
                    mapSequence.get(it);
                    mapSequence.put(it, mapSequence.get(it) + 1);
                } else {
                    mapSequence.put(it, 1);
                }
            }
            Map.Entry<Integer, Integer> frequentNumber = null;
            boolean chek = true;
            for (Map.Entry<Integer, Integer> it : mapSequence.entrySet()) {
                if (chek) {
                    frequentNumber = it;
                    chek = false;
                    continue;
                }
                if (it.getValue() > frequentNumber.getValue() || (it.getValue().equals(frequentNumber.getValue())
                        && it.getKey() < frequentNumber.getKey())) {
                    frequentNumber = it;
                }
            }

            for (Integer it : listSequence) {
                if (!frequentNumber.getKey().equals(it)) {
                    writer.write(it + "\n");
                }
            }
            if (frequentNumber != null) {
                for (int i = 0; i < frequentNumber.getValue(); i++) {
                    writer.write(frequentNumber.getKey() + "\n");
                }
            }
            writer.flush();
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
