package com.example.avnifinalyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyItemData {
    private final HashMap<String, MyItem> data = new HashMap<>();

    //constructor for MyItemData
    public MyItemData() {
            // --- ASIA ---
            data.put("Afghanistan", new MyItem("Afghanistan", "Asia", "Islam", "Yes", "No", "38M", 33.9391, 67.7100));
            data.put("Armenia", new MyItem("Armenia", "Asia", "Christianity", "No", "No", "3M", 40.0691, 45.0382));
            data.put("Azerbaijan", new MyItem("Azerbaijan", "Asia", "Islam", "No", "No", "10M", 40.1431, 47.5769));
            data.put("Bahrain", new MyItem("Bahrain", "Asia", "Islam", "No", "No", "1.6M", 26.0667, 50.5577));
            data.put("Bangladesh", new MyItem("Bangladesh", "Asia", "Islam", "No", "No", "164M", 23.6850, 90.3563));
            data.put("Bhutan", new MyItem("Bhutan", "Asia", "Buddhism", "Yes", "No", "0.8M", 27.5142, 90.4336));
            data.put("Brunei", new MyItem("Brunei", "Asia", "Islam", "No", "No", "0.4M", 4.5353, 114.7277));
            data.put("Cambodia", new MyItem("Cambodia", "Asia", "Buddhism", "No", "No", "16M", 12.5657, 104.9910));
            data.put("China", new MyItem("China", "Asia", "Atheist", "No", "No", "1.4B", 35.8617, 104.1954));
            data.put("Cyprus", new MyItem("Cyprus", "Asia", "Christianity", "No", "No", "1.2M", 35.1264, 33.4299));
            data.put("Georgia", new MyItem("Georgia", "Asia", "Christianity", "No", "Yes", "3.7M", 42.3154, 43.3569));
            data.put("India", new MyItem("India", "Asia", "Hinduism", "No", "No", "1.4B", 20.5937, 78.9629));
            data.put("Indonesia", new MyItem("Indonesia", "Asia", "Islam", "No", "No", "273M", -0.7893, 113.9213));
            data.put("Iran", new MyItem("Iran", "Asia", "Islam", "No", "No", "83M", 32.4279, 53.6880));
            data.put("Iraq", new MyItem("Iraq", "Asia", "Islam", "No", "No", "40M", 33.2232, 43.6793));
            data.put("Israel", new MyItem("Israel", "Asia", "Judaism", "No", "Yes", "9M", 31.0461, 34.8516));
            data.put("Japan", new MyItem("Japan", "Asia", "Buddhism", "No", "No", "125M", 36.2048, 138.2529));
            data.put("Jordan", new MyItem("Jordan", "Asia", "Islam", "No", "Yes", "10M", 30.5852, 36.2384));
            data.put("Kazakhstan", new MyItem("Kazakhstan", "Asia", "Islam", "Yes", "No", "19M", 48.0196, 66.9237));
            data.put("Kuwait", new MyItem("Kuwait", "Asia", "Islam", "No", "No", "4.3M", 29.3117, 47.4818));
            data.put("Kyrgyzstan", new MyItem("Kyrgyzstan", "Asia", "Islam", "Yes", "No", "6.5M", 41.2044, 74.7661));
            data.put("Laos", new MyItem("Laos", "Asia", "Buddhism", "Yes", "No", "7M", 19.8563, 102.4955));
            data.put("Lebanon", new MyItem("Lebanon", "Asia", "Islam", "No", "No", "6.8M", 33.8547, 35.8623));
            data.put("Malaysia", new MyItem("Malaysia", "Asia", "Islam", "No", "No", "32M", 4.2105, 101.9758));
            data.put("Maldives", new MyItem("Maldives", "Asia", "Islam", "No", "No", "0.5M", 3.2028, 73.2207));
            data.put("Mongolia", new MyItem("Mongolia", "Asia", "Buddhism", "Yes", "No", "3M", 46.8625, 103.8467));
            data.put("Myanmar", new MyItem("Myanmar", "Asia", "Buddhism", "No", "No", "54M", 21.9162, 95.9560));
            data.put("Nepal", new MyItem("Nepal", "Asia", "Hinduism", "Yes", "No", "29M", 28.3949, 84.1240));
            data.put("North Korea", new MyItem("North Korea", "Asia", "Atheist", "Yes", "No", "25M", 40.3399, 127.5101));
            data.put("Oman", new MyItem("Oman", "Asia", "Islam", "No", "No", "5M", 21.4735, 55.9754));
            data.put("Pakistan", new MyItem("Pakistan", "Asia", "Islam", "No", "No", "220M", 30.3753, 69.3451));
            data.put("Philippines", new MyItem("Philippines", "Asia", "Christianity", "No", "No", "109M", 12.8797, 121.7740));
            data.put("Qatar", new MyItem("Qatar", "Asia", "Islam", "No", "No", "2.8M", 25.3548, 51.1839));
            data.put("Saudi Arabia", new MyItem("Saudi Arabia", "Asia", "Islam", "No", "No", "35M", 23.8859, 45.0792));
            data.put("Singapore", new MyItem("Singapore", "Asia", "Buddhism", "No", "No", "5.7M", 1.3521, 103.8198));
            data.put("South Korea", new MyItem("South Korea", "Asia", "Christianity", "No", "No", "51M", 35.9078, 127.7669));
            data.put("Sri Lanka", new MyItem("Sri Lanka", "Asia", "Buddhism", "No", "No", "21M", 7.8731, 80.7718));
            data.put("Syria", new MyItem("Syria", "Asia", "Islam", "No", "No", "17M", 34.8021, 38.9968));
            data.put("Tajikistan", new MyItem("Tajikistan", "Asia", "Islam", "Yes", "No", "9.5M", 38.8610, 71.2761));
            data.put("Thailand", new MyItem("Thailand", "Asia", "Buddhism", "No", "No", "69M", 15.8700, 100.9925));
            data.put("Timor-Leste", new MyItem("Timor-Leste", "Asia", "Christianity", "No", "No", "1.3M", -8.8742, 125.7275));
            data.put("Turkey", new MyItem("Turkey", "Asia / Europe", "Islam", "No", "No", "84M", 38.9637, 35.2433));
            data.put("Turkmenistan", new MyItem("Turkmenistan", "Asia", "Islam", "Yes", "No", "6M", 38.9697, 59.5563));
            data.put("United Arab Emirates", new MyItem("United Arab Emirates", "Asia", "Islam", "No", "No", "9.9M", 23.4241, 53.8478));
            data.put("Uzbekistan", new MyItem("Uzbekistan", "Asia", "Islam", "Yes", "No", "34M", 41.3775, 64.5853));
            data.put("Vietnam", new MyItem("Vietnam", "Asia", "Buddhism", "No", "No", "96M", 14.0583, 108.2772));
            data.put("Yemen", new MyItem("Yemen", "Asia", "Islam", "No", "No", "30M", 15.5527, 48.5164));

            // --- EUROPE ---
            data.put("Albania", new MyItem("Albania", "Europe", "Islam", "No", "No", "2.8M", 41.1533, 20.1683));
            data.put("Andorra", new MyItem("Andorra", "Europe", "Christianity", "No", "Yes", "0.08M", 42.5063, 1.5218));
            data.put("Austria", new MyItem("Austria", "Europe", "Christianity", "No", "Yes", "9M", 47.5162, 14.5501));
            data.put("Belarus", new MyItem("Belarus", "Europe", "Christianity", "No", "No", "9.4M", 53.7098, 27.9534));
            data.put("Belgium", new MyItem("Belgium", "Europe", "Christianity", "No", "No", "11.6M", 50.5039, 4.4699));
            data.put("Bosnia and Herzegovina", new MyItem("Bosnia and Herzegovina", "Europe", "Islam", "No", "No", "3.3M", 43.9159, 17.6791));
            data.put("Bulgaria", new MyItem("Bulgaria", "Europe", "Christianity", "No", "Yes", "7M", 42.7339, 25.4858));
            data.put("Croatia", new MyItem("Croatia", "Europe", "Christianity", "No", "No", "4M", 45.1000, 15.2000));
            data.put("Cyprus", new MyItem("Cyprus", "Europe", "Christianity", "No", "No", "1.2M", 35.1264, 33.4299));
            data.put("Czech Republic", new MyItem("Czech Republic", "Europe", "Atheist", "No", "No", "10.7M", 49.8175, 15.4730));
            data.put("Denmark", new MyItem("Denmark", "Europe", "Christianity", "No", "No", "5.8M", 56.2639, 9.5018));
            data.put("Estonia", new MyItem("Estonia", "Europe", "Atheist", "No", "No", "1.3M", 58.5953, 25.0136));
            data.put("Finland", new MyItem("Finland", "Europe", "Christianity", "No", "No", "5.5M", 61.9241, 25.7482));
            data.put("France", new MyItem("France", "Europe", "Christianity", "No", "Yes", "67M", 46.2276, 2.2137));
            data.put("Germany", new MyItem("Germany", "Europe", "Christianity", "No", "Yes", "83M", 51.1657, 10.4515));
            data.put("Greece", new MyItem("Greece", "Europe", "Christianity", "No", "Yes", "10.7M", 39.0742, 21.8243));
            data.put("Hungary", new MyItem("Hungary", "Europe", "Christianity", "No", "No", "9.6M", 47.1625, 19.5033));
            data.put("Iceland", new MyItem("Iceland", "Europe", "Christianity", "No", "No", "0.36M", 64.9631, -19.0208));
            data.put("Ireland", new MyItem("Ireland", "Europe", "Christianity", "No", "No", "5M", 53.4129, -8.2439));
            data.put("Italy", new MyItem("Italy", "Europe", "Christianity", "No", "Yes", "60M", 41.8719, 12.5674));
            data.put("Kosovo", new MyItem("Kosovo", "Europe", "Islam", "No", "No", "1.8M", 42.6026, 20.9030));
            data.put("Latvia", new MyItem("Latvia", "Europe", "Christianity", "No", "No", "1.9M", 56.8796, 24.6032));
            data.put("Liechtenstein", new MyItem("Liechtenstein", "Europe", "Christianity", "Yes", "No", "0.04M", 47.1660, 9.5554));
            data.put("Lithuania", new MyItem("Lithuania", "Europe", "Christianity", "No", "No", "2.8M", 55.1694, 23.8813));
            data.put("Luxembourg", new MyItem("Luxembourg", "Europe", "Christianity", "No", "No", "0.63M", 49.8153, 6.1296));
            data.put("Malta", new MyItem("Malta", "Europe", "Christianity", "No", "No", "0.52M", 35.9375, 14.3754));
            data.put("Moldova", new MyItem("Moldova", "Europe", "Christianity", "No", "No", "2.6M", 47.4116, 28.3699));
            data.put("Monaco", new MyItem("Monaco", "Europe", "Christianity", "No", "No", "0.04M", 43.7384, 7.4246));
            data.put("Montenegro", new MyItem("Montenegro", "Europe", "Christianity", "No", "No", "0.62M", 42.7087, 19.3744));
            data.put("Netherlands", new MyItem("Netherlands", "Europe", "Christianity", "No", "No", "17M", 52.1326, 5.2913));
            data.put("North Macedonia", new MyItem("North Macedonia", "Europe", "Christianity", "No", "No", "2.1M", 41.6086, 21.7453));
            data.put("Norway", new MyItem("Norway", "Europe", "Christianity", "No", "No", "5.4M", 60.4720, 8.4689));
            data.put("Poland", new MyItem("Poland", "Europe", "Christianity", "No", "No", "38M", 51.9194, 19.1451));
            data.put("Portugal", new MyItem("Portugal", "Europe", "Christianity", "No", "No", "10.3M", 39.3999, -8.2245));
            data.put("Romania", new MyItem("Romania", "Europe", "Christianity", "No", "No", "19M", 45.9432, 24.9668));
            data.put("Russia", new MyItem("Russia", "Europe / Asia", "Christianity", "Yes", "No", "144M", 61.5240, 105.3188));
            data.put("San Marino", new MyItem("San Marino", "Europe", "Christianity", "No", "No", "0.03M", 43.9424, 12.4578));
            data.put("Serbia", new MyItem("Serbia", "Europe", "Christianity", "No", "No", "6.9M", 44.0165, 21.0059));
            data.put("Slovakia", new MyItem("Slovakia", "Europe", "Christianity", "No", "No", "5.4M", 48.6690, 19.6990));
            data.put("Slovenia", new MyItem("Slovenia", "Europe", "Christianity", "No", "No", "2.1M", 46.1512, 14.9955));
            data.put("Spain", new MyItem("Spain", "Europe", "Christianity", "No", "Yes", "47M", 40.4637, -3.7492));
            data.put("Sweden", new MyItem("Sweden", "Europe", "Christianity", "No", "No", "10M", 60.1282, 18.6435));
            data.put("Switzerland", new MyItem("Switzerland", "Europe", "Christianity", "No", "Yes", "8.6M", 46.8182, 8.2275));
            data.put("Ukraine", new MyItem("Ukraine", "Europe", "Christianity", "No", "No", "41M", 48.3794, 31.1656));
            data.put("United Kingdom", new MyItem("United Kingdom", "Europe", "Christianity", "No", "No", "67M", 55.3781, -3.4360));
            data.put("Vatican City", new MyItem("Vatican City", "Europe", "Christianity", "No", "No", "0.0008M", 41.9029, 12.4534));

            // --- NORTH AMERICA ---
            data.put("Canada", new MyItem("Canada", "North America", "Christianity", "No", "No", "38M", 56.1304, -106.3468));
            data.put("United States", new MyItem("United States", "North America", "Christianity", "No", "Yes", "331M", 37.0902, -95.7129));
            data.put("Mexico", new MyItem("Mexico", "North America", "Christianity", "No", "No", "128M", 23.6345, -102.5528));
            data.put("Guatemala", new MyItem("Guatemala", "North America", "Christianity", "No", "No", "17M", 15.7835, -90.2308));
            data.put("Belize", new MyItem("Belize", "North America", "Christianity", "No", "No", "0.39M", 17.1899, -88.4976));
            data.put("El Salvador", new MyItem("El Salvador", "North America", "Christianity", "No", "No", "6.5M", 13.7942, -88.8965));
            data.put("Honduras", new MyItem("Honduras", "North America", "Christianity", "No", "No", "9.5M", 15.1999, -86.2419));
            data.put("Nicaragua", new MyItem("Nicaragua", "North America", "Christianity", "No", "No", "6.6M", 12.8654, -85.2072));
            data.put("Costa Rica", new MyItem("Costa Rica", "North America", "Christianity", "No", "No", "5M", 9.7489, -83.7534));
            data.put("Panama", new MyItem("Panama", "North America", "Christianity", "No", "No", "4.3M", 8.5380, -80.7821));
            data.put("Cuba", new MyItem("Cuba", "North America", "Christianity", "No", "No", "11M", 21.5218, -77.7812));
            data.put("Dominican Republic", new MyItem("Dominican Republic", "North America", "Christianity", "No", "No", "10.8M", 18.7357, -70.1627));
            data.put("Haiti", new MyItem("Haiti", "North America", "Christianity", "No", "No", "11M", 18.9712, -72.2852));
            data.put("Jamaica", new MyItem("Jamaica", "North America", "Christianity", "No", "No", "2.9M", 18.1096, -77.2975));
            data.put("Bahamas", new MyItem("Bahamas", "North America", "Christianity", "No", "No", "0.39M", 25.0343, -77.3963));
            data.put("Barbados", new MyItem("Barbados", "North America", "Christianity", "No", "No", "0.287M", 13.1939, -59.5432));
            data.put("Trinidad and Tobago", new MyItem("Trinidad and Tobago", "North America", "Christianity", "No", "No", "1.4M", 10.6918, -61.2225));
            data.put("Saint Lucia", new MyItem("Saint Lucia", "North America", "Christianity", "No", "No", "0.183M", 13.9094, -60.9789));
            data.put("Saint Vincent and the Grenadines", new MyItem("Saint Vincent and the Grenadines", "North America", "Christianity", "No", "No", "0.111M", 12.9843, -61.2872));
            data.put("Antigua and Barbuda", new MyItem("Antigua and Barbuda", "North America", "Christianity", "No", "No", "0.098M", 17.0608, -61.7964));

            // --- SOUTH AMERICA ---
            data.put("Argentina", new MyItem("Argentina", "South America", "Christianity", "No", "No", "45M", -38.4161, -63.6167));
            data.put("Bolivia", new MyItem("Bolivia", "South America", "Christianity", "Yes", "No", "11.8M", -16.2902, -63.5887));
            data.put("Brazil", new MyItem("Brazil", "South America", "Christianity", "No", "No", "212M", -14.2350, -51.9253));
            data.put("Chile", new MyItem("Chile", "South America", "Christianity", "No", "No", "19M", -35.6751, -71.5430));
            data.put("Colombia", new MyItem("Colombia", "South America", "Christianity", "No", "No", "50M", 4.5709, -74.2973));
            data.put("Ecuador", new MyItem("Ecuador", "South America", "Christianity", "No", "No", "17.6M", -1.8312, -78.1834));
            data.put("Guyana", new MyItem("Guyana", "South America", "Christianity", "No", "No", "0.79M", 4.8604, -58.9302));
            data.put("Paraguay", new MyItem("Paraguay", "South America", "Christianity", "No", "No", "7M", -23.4425, -58.4438));
            data.put("Peru", new MyItem("Peru", "South America", "Christianity", "No", "No", "33M", -9.1900, -75.0152));
            data.put("Suriname", new MyItem("Suriname", "South America", "Christianity", "No", "No", "0.6M", 3.9193, -56.0278));
            data.put("Uruguay", new MyItem("Uruguay", "South America", "Christianity", "No", "No", "3.5M", -32.5228, -55.7658));
            data.put("Venezuela", new MyItem("Venezuela", "South America", "Christianity", "No", "No", "28M", 6.4238, -66.5897));
        }

    // Returns a single country's info
    public MyItem getCountryInfo(String country) {
        MyItem item = data.get(country);
        if (item == null) {
            return new MyItem(country, "Unknown", "Unknown", "Unknown", "No", "Unknown", 0.0, 0.0);
        }
        return item;
    }

    // Returns a list of all MyItem objects
    public List<MyItem> getItems() {
        return new ArrayList<>(data.values());
    }

    //Returns a random country from the list of countries
    public MyItem getRandomCountry() {
            ArrayList<MyItem> list = new ArrayList<>(getItems()); // all countries
            if (list.isEmpty()) return null;

            int index = new java.util.Random().nextInt(list.size());
            return list.get(index);
    }

}


