/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Feliciana Gunadi
 */
public class CounterGenre {
    public int[] genreCount(int[] counterGenre, String genre) {
        switch (genre) {
            case "Horror":
                counterGenre[0]++;
                break;
            case "Fantasi":
                counterGenre[1]++;
                break;
            case "Sci-Fi":
                counterGenre[2]++;
                break;
            case "Romantis":
                counterGenre[3]++;
                break;
            case "Komedi":
                counterGenre[4]++;
                break;
            case "Misteri":
                counterGenre[5]++;
                break;
            case "Drama":
                counterGenre[6]++;
                break;
            case "Biografi":
                counterGenre[7]++;
                break;
            case "Ensiklopedia":
                counterGenre[8]++;
                break;
            case "Pengetahuan":
                counterGenre[9]++;
                break;
            case "Kamus":
                counterGenre[10]++;
                break;
            case "Filsafat":
                counterGenre[11]++;
                break;
            case "Sejarah":
                counterGenre[12]++;
                break;
            case "Psikologi":
                counterGenre[13]++;
                break;
            case "Lainnya":
                counterGenre[14]++;
                break;
            default:
                break;
        }
        return counterGenre;
    }
}
