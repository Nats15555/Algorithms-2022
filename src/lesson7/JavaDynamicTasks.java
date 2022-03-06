package lesson7;

import kotlin.NotImplementedError;

import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    // Время О(mn)
    // Память О(mn)
    public static String longestCommonSubSequence(String first, String second) {
        int lengthF = first.length();
        int lengthS = second.length();
        if (lengthF == 0 || lengthS == 0) {
            return "";
        }

        int[][] matrix = new int[lengthF][lengthS];

        int buff = 0;
        for (int i = 0; i < lengthS; i++) {
            if (first.charAt(0) == second.charAt(i)) {
                buff = 1;
            }
            matrix[0][i] = buff;
        }
        buff = 0;
        for (int i = 0; i < lengthF; i++) {
            if (second.charAt(0) == first.charAt(i)) {
                buff = 1;
            }
            matrix[i][0] = buff;
        }

        for (int i = 1; i < lengthF; i++) {
            for (int j = 1; j < lengthS; j++) {
                if (first.charAt(i) == second.charAt(j)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }

        }

        StringBuilder str = new StringBuilder();
        lengthF--;
        lengthS--;
        if (matrix[lengthF][lengthS] == 0) {
            return "";
        }
        while (lengthS != 0 && lengthF != 0) {
            if ((matrix[lengthF - 1][lengthS - 1] == matrix[lengthF][lengthS] - 1) &&
                    (first.charAt(lengthF) == second.charAt(lengthS))) {
                str.append(first.charAt(lengthF));
                lengthS--;
                lengthF--;
            } else if (matrix[lengthF - 1][lengthS] == matrix[lengthF][lengthS]) {
                lengthF--;
            } else {
                lengthS--;
            }
        }

        if (matrix[lengthF][lengthS] != 0) {
            if (lengthS == 0 && matrix[0][lengthS] != 0) {
                lengthF = 0;
            }
            str.append(first.charAt(lengthF));
        }
        str.reverse();
        return str.toString();

    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    //Время О(nlogn)
    //Память O(n)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int[] buffIndexes = new int[list.size()];
        int[] reversLis = new int[list.size() + 1];
        int length = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int buffLength = binarySearchIndex(list, length, reversLis, i);
            buffIndexes[i] = reversLis[buffLength - 1];
            reversLis[buffLength] = i;
            if (buffLength > length) {
                length = buffLength;
            }
        }
        int[] lis = new int[length];
        int index = reversLis[length];
        List<Integer> result = new ArrayList<>();
        for (int i = length - 1; i >= 0; i--) {
            lis[i] = list.get(index);
            result.add(lis[i]);
            index = buffIndexes[index];
        }
        return result;
    }

    private static int binarySearchIndex(List<Integer> list, int high, int[] reversLis, int index) {
        int low = 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(reversLis[mid]) <= list.get(index)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }
}
