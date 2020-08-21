package com.ewo.laddemo.localdb;

import java.util.Random;

public class DummyItemGenerator {


    private static final String URL_STAR_TREK = "https://m.media-amazon.com/images/M/MV5BYWIwMTI4NzctZmNjZi00OGU2LThhMGItZDI0ODAwOWI1NTFlXkEyXkFqcGdeQXVyNTE1NjY5Mg@@._V1_SY1000_CR0,0,706,1000_AL_.jpg";
    private static final String URL_STAR_WARS = "https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SY999_SX666_AL_.jpg";
    private static final String URL_FUTURAMA = "https://m.media-amazon.com/images/M/MV5BNzA2ZDk2ZTUtMWU2Yi00NDVmLTk1ODEtMmFmMjQyNWYzODI0XkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SY1000_CR0,0,650,1000_AL_.jpg";
    private static final String URL_STAR_GATE = "https://m.media-amazon.com/images/M/MV5BMTc3MjEwMTc5N15BMl5BanBnXkFtZTcwNzQ2NjQ4NA@@._V1_SY1000_CR0,0,666,1000_AL_.jpg";
    private static final String URL_TERMINATOR = "https://m.media-amazon.com/images/M/MV5BMGU2NzRmZjUtOGUxYS00ZjdjLWEwZWItY2NlM2JhNjkxNTFmXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SY999_CR0,0,672,999_AL_.jpg";
    private static final String URL_WITCHER = "https://m.media-amazon.com/images/M/MV5BOGE4MmVjMDgtMzIzYy00NjEwLWJlODMtMDI1MGY2ZDlhMzE2XkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_SY1000_CR0,0,674,1000_AL_.jpg";

    public static MovieModel GenerateNewItem(EnumViewCounterType counterType) {
        int randomIndex = new Random().nextInt(6);
        return GenerateNewItem(randomIndex, counterType);
    }

    public static MovieModel GenerateNewItem(int dummyItemIndex, EnumViewCounterType counterType) {
        // Perform local operations
        MovieModel[] movies = new MovieModel[]{
                new MovieModel("Star Trek Voyager", 7.8f, 1995, URL_STAR_TREK, counterType),
                new MovieModel("Star Wars Gizli Tehlike", 6.5f, 1999, URL_STAR_WARS, counterType),
                new MovieModel("Stargate SG-1", 8.4f, 1997, URL_STAR_GATE, counterType),
                new MovieModel("Futurama", 8.4f, 1999, URL_FUTURAMA, counterType),
                new MovieModel("Terminator 2: Mahşer Günü", 8.5f, 1991, URL_TERMINATOR, counterType),
                new MovieModel("The Witcher", 8.2f, 2019, URL_WITCHER, counterType)
        };
        return movies[dummyItemIndex];
    }
}