import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в одну строку:");
        String exp = scanner.nextLine().trim(); // строка записана без лишних пробелов
        System.out.println(calc(exp));
    }



    public static String calc(String input) throws IOException{
        // Получаем строку с выражением
        String[] romans = {"I","II","III","IV","V","VI","VII","VIII","IX","X",""};
        String[] arabians = {"0","1","2","3","4","5","6","7","8","9","10"};
        int[] arabianForRoman = {1,2,3,4,5,6,7,8,9,10,0};

//        эти массивы использвуются для поиска операторов
        String[] actions = {"+","-","*","/"};
        String[] regexActions = {"\\+","-","\\*","/"};

        int indexAction = 0; //числовой индекс для поиска операторов
        int total = 0;  //итог подсчётов калькулятора
        String result = "";

//        переменные для калькулятора арабских чисел
        int a;
        int b;

//        флажки для разделения программы на римский кальк и арабский
        boolean romanFlag = false;
        boolean arabianFlag = false;

//        определяем формат выражения
        for(int j = 0; j < romans.length; j++) {
            if (input.contains(romans[j])) {
                romanFlag = true;
            }
            if (input.contains(arabians[j])) {
                arabianFlag = true;
                break;
            }
        }
        if (romanFlag == arabianFlag){
            throw new IOException("//т.к. используются одновременно разные системы счисления");
        }

//            определяем знак выражения
        for(int i=0; i < actions.length; i++){
            if (input.contains(actions[i])){
                indexAction = i;
                break;
            }else {
                indexAction = -1;
            }
        }
        if (indexAction==-1){
            throw new IOException("//т.к. строка не является математической операцией");
        }

//            разделение строки на переменные
        String[] values = input.split(regexActions[indexAction]);
        if (values.length > 2){
            throw new IOException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

//        калькултор арабских чисел
        if (arabianFlag){
            //            преобразование из строки в число
            a = Integer.parseInt(values[0]);
            b = Integer.parseInt(values[1]);

            if(a > 10 || b > 10 || a < 1 || b < 1){
                throw new IOException("//т.к. переменные выражения не соответствует требованию от 1 до 10 включительно");
            }

            //            вычисления
            switch (indexAction) {
                case (0) -> {
                    total = a + b;
                    result = Integer.toString(total);
                }
                case (1) -> {
                    total = a - b;
                    result = Integer.toString(total);
                }
                case (2) -> {
                    total = a * b;
                    result = Integer.toString(total);
                }
                case (3) -> {
                    total = a / b;
                    result = Integer.toString(total);
                }
            }
        }


//        калькулятор римских цифр
        if (romanFlag){
            int[] valuesArabian = {0,0};

            //            преоброзование римских цифр в арабские
            for (int i = 0; i < values.length; i++){
                for(int z = 0; z < romans.length;z++){
                    if (Objects.equals(values[i], romans[z])){
                        valuesArabian[i] = arabianForRoman[z];
                        break;
                    }
                }
            }
            //            вычисления
            switch (indexAction) {
                case (0) -> total = valuesArabian[0] + valuesArabian[1];
                case (1) -> {
                    total = valuesArabian[0] - valuesArabian[1];
                    if (total < 0) {
                        throw new IOException("//т.к. в римской системе нет отрицательных чисел и нуля");
                    }
                }
                case (2) -> total = valuesArabian[0] * valuesArabian[1];
                case (3) -> {
                    total = valuesArabian[0] / valuesArabian[1];
                    if (total < 1) {
                        throw new IOException("//т.к. в римской системе нет отрицательных чисел и нуля");
                    }
                }
            }
            //            конвертация результата в римские цифры
            if (total <= 10){
                for (int i = 0; i < arabianForRoman.length;i++){
                    if (total == arabianForRoman[i]) {
                        result+= romans[i];
                        break;
                    }
                }

            } else {
                int lastDigit = total%10;
                int firstDigit = total/10;
                switch (firstDigit) {
                    case (1) -> {
                        result += "X";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (2) -> {
                        result += "XX";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (3) -> {
                        result += "XXX";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (4) -> {
                        result += "XL";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (5) -> {
                        result += "L";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (6) -> {
                        result += "LX";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (7) -> {
                        result += "LXX";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (8) -> {
                        result += "LXXX";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (9) -> {
                        result += "XC";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                    case (10) -> {
                        result += "C";
                        for (int i = 0; i < arabianForRoman.length; i++) {
                            if (lastDigit == arabianForRoman[i]) {
                                result += romans[i];
                                break;
                            }
                        }
                    }
                }
            }
        }
    return result;
    }
}