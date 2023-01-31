package P1713_후보_추천하기;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Samsung_SDS_Algorithm_lecture/src/P1713_후보_추천하기/input.txt"));
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int T = scanner.nextInt();

        ArrayList<people> pictures = new ArrayList<>();
        people[] p = new people[101];

        for (int i = 0; i < 101; i++) {
            p[i] = new people(i, 0, 0, false);
        }

        for (int t = 0; t < T; t++) {
            int recommend = scanner.nextInt();

            if (p[recommend].isIn == false) {

                if (pictures.size() == N) {
                    int tempPeopleNumber = pictures.get(pictures.size() - 1).peopleNumber;
                    p[tempPeopleNumber].timeStamp = 0;
                    p[tempPeopleNumber].isIn = false;
                    p[tempPeopleNumber].count = 0;

                    pictures.remove(pictures.size() - 1);
                }

                p[recommend].setCount(1);
                p[recommend].setIn(true);
                p[recommend].setTimeStamp(t);
                pictures.add(p[recommend]);
            } else {
                for (int i = 0; i < pictures.size(); i++) {
                    if (pictures.get(i).peopleNumber == recommend) {
                        pictures.get(i).count++;
                        break;
                    }
                }
            }

            Collections.sort(pictures);

        }

        Collections.sort(pictures, new Comparator<people>() {
            @Override
            public int compare(people o1, people o2) {
                return o1.peopleNumber - o2.peopleNumber;
            }
        });

        for (int i = 0; i < pictures.size(); i++) {
            System.out.print(pictures.get(i).peopleNumber + " ");
        }

    }
}

class people implements Comparable<people> {
    int peopleNumber;
    int count;
    int timeStamp;
    boolean isIn;

    public people(int peopleNumber, int count, int timeStamp, boolean isIn) {
        this.peopleNumber = peopleNumber;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    public int getCount() {
        return count;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public boolean isIn() {
        return isIn;
    }

    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setIn(boolean in) {
        isIn = in;
    }

    @Override
    public int compareTo(people o2) {
        int comp1 = o2.count - count;

        if (comp1 == 0) return o2.timeStamp - timeStamp;
        else return comp1;
    }
}
