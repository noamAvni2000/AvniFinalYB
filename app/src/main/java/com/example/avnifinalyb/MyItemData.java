package com.example.avnifinalyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyItemData {
    private static final HashMap<String, MyItem> data = new HashMap<>();

    static {
            // --- ASIA ---
            data.put("Afghanistan", new MyItem("Afghanistan", "Asia", "Islam", "Yes", "No", "38M"));
            data.put("Armenia", new MyItem("Armenia", "Asia", "Christianity", "No", "No", "3M"));
            data.put("Azerbaijan", new MyItem("Azerbaijan", "Asia", "Islam", "No", "No", "10M"));
            data.put("Bahrain", new MyItem("Bahrain", "Asia", "Islam", "No", "No", "1.6M"));
            data.put("Bangladesh", new MyItem("Bangladesh", "Asia", "Islam", "No", "No", "164M"));
            data.put("Bhutan", new MyItem("Bhutan", "Asia", "Buddhism", "Yes", "No", "0.8M"));
            data.put("Brunei", new MyItem("Brunei", "Asia", "Islam", "No", "No", "0.4M"));
            data.put("Cambodia", new MyItem("Cambodia", "Asia", "Buddhism", "No", "No", "16M"));
            data.put("China", new MyItem("China", "Asia", "Mixed", "No", "No", "1.4B"));
            data.put("Cyprus", new MyItem("Cyprus", "Asia", "Christianity", "No", "No", "1.2M"));
            data.put("Georgia", new MyItem("Georgia", "Asia", "Christianity", "No", "Yes", "3.7M"));
            data.put("India", new MyItem("India", "Asia", "Hinduism", "No", "No", "1.4B"));
            data.put("Indonesia", new MyItem("Indonesia", "Asia", "Islam", "No", "No", "273M"));
            data.put("Iran", new MyItem("Iran", "Asia", "Islam", "No", "No", "83M"));
            data.put("Iraq", new MyItem("Iraq", "Asia", "Islam", "No", "No", "40M"));
            data.put("Israel", new MyItem("Israel", "Asia", "Judaism", "No", "Yes", "9M"));
            data.put("Japan", new MyItem("Japan", "Asia", "Shinto/Buddhism", "No", "No", "125M"));
            data.put("Jordan", new MyItem("Jordan", "Asia", "Islam", "No", "Yes", "10M"));
            data.put("Kazakhstan", new MyItem("Kazakhstan", "Asia", "Islam/Christianity", "Yes", "No", "19M"));
            data.put("Kuwait", new MyItem("Kuwait", "Asia", "Islam", "No", "No", "4.3M"));
            data.put("Kyrgyzstan", new MyItem("Kyrgyzstan", "Asia", "Islam", "Yes", "No", "6.5M"));
            data.put("Laos", new MyItem("Laos", "Asia", "Buddhism", "Yes", "No", "7M"));
            data.put("Lebanon", new MyItem("Lebanon", "Asia", "Christianity / Islam", "No", "No", "6.8M"));
            data.put("Malaysia", new MyItem("Malaysia", "Asia", "Islam", "No", "No", "32M"));
            data.put("Maldives", new MyItem("Maldives", "Asia", "Islam", "No", "No", "0.5M"));
            data.put("Mongolia", new MyItem("Mongolia", "Asia", "Buddhism", "Yes", "No", "3M"));
            data.put("Myanmar", new MyItem("Myanmar", "Asia", "Buddhism", "No", "No", "54M"));
            data.put("Nepal", new MyItem("Nepal", "Asia", "Hinduism / Buddhism", "Yes", "No", "29M"));
            data.put("North Korea", new MyItem("North Korea", "Asia", "Atheist / Juche", "Yes", "No", "25M"));
            data.put("Oman", new MyItem("Oman", "Asia", "Islam", "No", "No", "5M"));
            data.put("Pakistan", new MyItem("Pakistan", "Asia", "Islam", "No", "No", "220M"));
            data.put("Philippines", new MyItem("Philippines", "Asia", "Christianity", "No", "No", "109M"));
            data.put("Qatar", new MyItem("Qatar", "Asia", "Islam", "No", "No", "2.8M"));
            data.put("Saudi Arabia", new MyItem("Saudi Arabia", "Asia", "Islam", "No", "No", "35M"));
            data.put("Singapore", new MyItem("Singapore", "Asia", "Mixed", "No", "No", "5.7M"));
            data.put("South Korea", new MyItem("South Korea", "Asia", "Christianity / Buddhism", "No", "No", "51M"));
            data.put("Sri Lanka", new MyItem("Sri Lanka", "Asia", "Buddhism", "No", "No", "21M"));
            data.put("Syria", new MyItem("Syria", "Asia", "Islam / Christianity", "No", "No", "17M"));
            data.put("Tajikistan", new MyItem("Tajikistan", "Asia", "Islam", "Yes", "No", "9.5M"));
            data.put("Thailand", new MyItem("Thailand", "Asia", "Buddhism", "No", "No", "69M"));
            data.put("Timor-Leste", new MyItem("Timor-Leste", "Asia", "Christianity", "No", "No", "1.3M"));
            data.put("Turkey", new MyItem("Turkey", "Asia / Europe", "Islam", "No", "No", "84M"));
            data.put("Turkmenistan", new MyItem("Turkmenistan", "Asia", "Islam", "Yes", "No", "6M"));
            data.put("United Arab Emirates", new MyItem("United Arab Emirates", "Asia", "Islam", "No", "No", "9.9M"));
            data.put("Uzbekistan", new MyItem("Uzbekistan", "Asia", "Islam", "Yes", "No", "34M"));
            data.put("Vietnam", new MyItem("Vietnam", "Asia", "Buddhism / Folk", "No", "No", "96M"));
            data.put("Yemen", new MyItem("Yemen", "Asia", "Islam", "No", "No", "30M"));

            // --- EUROPE ---
            data.put("Albania", new MyItem("Albania", "Europe", "Islam / Christianity", "No", "No", "2.8M"));
            data.put("Andorra", new MyItem("Andorra", "Europe", "Christianity", "No", "Yes", "0.08M"));
            data.put("Austria", new MyItem("Austria", "Europe", "Christianity", "No", "Yes", "9M"));
            data.put("Belarus", new MyItem("Belarus", "Europe", "Christianity", "No", "No", "9.4M"));
            data.put("Belgium", new MyItem("Belgium", "Europe", "Christianity / Secular", "No", "No", "11.6M"));
            data.put("Bosnia and Herzegovina", new MyItem("Bosnia and Herzegovina", "Europe", "Islam / Christianity", "No", "No", "3.3M"));
            data.put("Bulgaria", new MyItem("Bulgaria", "Europe", "Christianity", "No", "Yes", "7M"));
            data.put("Croatia", new MyItem("Croatia", "Europe", "Christianity", "No", "No", "4M"));
            data.put("Cyprus", new MyItem("Cyprus", "Europe", "Christianity / Islam", "No", "No", "1.2M"));
            data.put("Czech Republic", new MyItem("Czech Republic", "Europe", "Christianity / Nonreligious", "No", "No", "10.7M"));
            data.put("Denmark", new MyItem("Denmark", "Europe", "Christianity", "No", "No", "5.8M"));
            data.put("Estonia", new MyItem("Estonia", "Europe", "Christianity / Nonreligious", "No", "No", "1.3M"));
            data.put("Finland", new MyItem("Finland", "Europe", "Christianity / Nonreligious", "No", "No", "5.5M"));
            data.put("France", new MyItem("France", "Europe", "Christianity / Secular", "No", "Yes", "67M"));
            data.put("Germany", new MyItem("Germany", "Europe", "Christianity / Secular", "No", "Yes", "83M"));
            data.put("Greece", new MyItem("Greece", "Europe", "Christianity", "No", "Yes", "10.7M"));
            data.put("Hungary", new MyItem("Hungary", "Europe", "Christianity", "No", "No", "9.6M"));
            data.put("Iceland", new MyItem("Iceland", "Europe", "Christianity", "No", "No", "0.36M"));
            data.put("Ireland", new MyItem("Ireland", "Europe", "Christianity", "No", "No", "5M"));
            data.put("Italy", new MyItem("Italy", "Europe", "Christianity", "No", "Yes", "60M"));
            data.put("Kosovo", new MyItem("Kosovo", "Europe", "Islam / Christianity", "No", "No", "1.8M"));
            data.put("Latvia", new MyItem("Latvia", "Europe", "Christianity / Nonreligious", "No", "No", "1.9M"));
            data.put("Liechtenstein", new MyItem("Liechtenstein", "Europe", "Christianity", "Yes", "No", "0.04M"));
            data.put("Lithuania", new MyItem("Lithuania", "Europe", "Christianity", "No", "No", "2.8M"));
            data.put("Luxembourg", new MyItem("Luxembourg", "Europe", "Christianity", "No", "No", "0.63M"));
            data.put("Malta", new MyItem("Malta", "Europe", "Christianity", "No", "No", "0.52M"));
            data.put("Moldova", new MyItem("Moldova", "Europe", "Christianity", "No", "No", "2.6M"));
            data.put("Monaco", new MyItem("Monaco", "Europe", "Christianity", "No", "No", "0.04M"));
            data.put("Montenegro", new MyItem("Montenegro", "Europe", "Christianity / Islam", "No", "No", "0.62M"));
            data.put("Netherlands", new MyItem("Netherlands", "Europe", "Christianity / Secular", "No", "No", "17M"));
            data.put("North Macedonia", new MyItem("North Macedonia", "Europe", "Christianity / Islam", "No", "No", "2.1M"));
            data.put("Norway", new MyItem("Norway", "Europe", "Christianity", "No", "No", "5.4M"));
            data.put("Poland", new MyItem("Poland", "Europe", "Christianity", "No", "No", "38M"));
            data.put("Portugal", new MyItem("Portugal", "Europe", "Christianity", "No", "No", "10.3M"));
            data.put("Romania", new MyItem("Romania", "Europe", "Christianity", "No", "No", "19M"));
            data.put("Russia", new MyItem("Russia", "Europe / Asia", "Christianity / Islam", "Yes", "No", "144M"));
            data.put("San Marino", new MyItem("San Marino", "Europe", "Christianity", "No", "No", "0.03M"));
            data.put("Serbia", new MyItem("Serbia", "Europe", "Christianity", "No", "No", "6.9M"));
            data.put("Slovakia", new MyItem("Slovakia", "Europe", "Christianity", "No", "No", "5.4M"));
            data.put("Slovenia", new MyItem("Slovenia", "Europe", "Christianity", "No", "No", "2.1M"));
            data.put("Spain", new MyItem("Spain", "Europe", "Christianity", "No", "Yes", "47M"));
            data.put("Sweden", new MyItem("Sweden", "Europe", "Christianity / Secular", "No", "No", "10M"));
            data.put("Switzerland", new MyItem("Switzerland", "Europe", "Christianity / Secular", "No", "Yes", "8.6M"));
            data.put("Ukraine", new MyItem("Ukraine", "Europe", "Christianity", "No", "No", "41M"));
            data.put("United Kingdom", new MyItem("United Kingdom", "Europe", "Christianity / Secular", "No", "No", "67M"));
            data.put("Vatican City", new MyItem("Vatican City", "Europe", "Christianity", "No", "No", "0.0008M"));

            // --- NORTH AMERICA ---
            data.put("Canada", new MyItem("Canada", "North America", "Christianity / Secular", "No", "No", "38M"));
            data.put("United States", new MyItem("United States", "North America", "Christianity / Secular", "No", "Yes", "331M"));
            data.put("Mexico", new MyItem("Mexico", "North America", "Christianity", "No", "No", "128M"));
            data.put("Guatemala", new MyItem("Guatemala", "North America", "Christianity", "No", "No", "17M"));
            data.put("Belize", new MyItem("Belize", "North America", "Christianity", "No", "No", "0.39M"));
            data.put("El Salvador", new MyItem("El Salvador", "North America", "Christianity", "No", "No", "6.5M"));
            data.put("Honduras", new MyItem("Honduras", "North America", "Christianity", "No", "No", "9.5M"));
            data.put("Nicaragua", new MyItem("Nicaragua", "North America", "Christianity", "No", "No", "6.6M"));
            data.put("Costa Rica", new MyItem("Costa Rica", "North America", "Christianity", "No", "No", "5M"));
            data.put("Panama", new MyItem("Panama", "North America", "Christianity", "No", "No", "4.3M"));
            data.put("Cuba", new MyItem("Cuba", "North America", "Christianity / Secular", "No", "No", "11M"));
            data.put("Dominican Republic", new MyItem("Dominican Republic", "North America", "Christianity", "No", "No", "10.8M"));
            data.put("Haiti", new MyItem("Haiti", "North America", "Christianity / Vodou", "No", "No", "11M"));
            data.put("Jamaica", new MyItem("Jamaica", "North America", "Christianity", "No", "No", "2.9M"));
            data.put("Bahamas", new MyItem("Bahamas", "North America", "Christianity", "No", "No", "0.39M"));
            data.put("Barbados", new MyItem("Barbados", "North America", "Christianity", "No", "No", "0.287M"));
            data.put("Trinidad and Tobago", new MyItem("Trinidad and Tobago", "North America", "Christianity / Hinduism", "No", "No", "1.4M"));
            data.put("Saint Lucia", new MyItem("Saint Lucia", "North America", "Christianity", "No", "No", "0.183M"));
            data.put("Saint Vincent and the Grenadines", new MyItem("Saint Vincent and the Grenadines", "North America", "Christianity", "No", "No", "0.111M"));
            data.put("Antigua and Barbuda", new MyItem("Antigua and Barbuda", "North America", "Christianity", "No", "No", "0.098M"));

            // --- SOUTH AMERICA ---
            data.put("Argentina", new MyItem("Argentina", "South America", "Christianity", "No", "No", "45M"));
            data.put("Bolivia", new MyItem("Bolivia", "South America", "Christianity / Indigenous", "Yes", "No", "11.8M"));
            data.put("Brazil", new MyItem("Brazil", "South America", "Christianity", "No", "No", "212M"));
            data.put("Chile", new MyItem("Chile", "South America", "Christianity", "No", "No", "19M"));
            data.put("Colombia", new MyItem("Colombia", "South America", "Christianity", "No", "No", "50M"));
            data.put("Ecuador", new MyItem("Ecuador", "South America", "Christianity", "No", "No", "17.6M"));
            data.put("Guyana", new MyItem("Guyana", "South America", "Christianity / Hinduism", "No", "No", "0.79M"));
            data.put("Paraguay", new MyItem("Paraguay", "South America", "Christianity", "No", "No", "7M"));
            data.put("Peru", new MyItem("Peru", "South America", "Christianity / Indigenous", "No", "No", "33M"));
            data.put("Suriname", new MyItem("Suriname", "South America", "Christianity / Hinduism / Islam", "No", "No", "0.6M"));
            data.put("Uruguay", new MyItem("Uruguay", "South America", "Christianity", "No", "No", "3.5M"));
            data.put("Venezuela", new MyItem("Venezuela", "South America", "Christianity", "No", "No", "28M"));
        }

    // Returns a single country's info
    public static MyItem getCountryInfo(String country) {
        MyItem item = data.get(country);
        if (item == null) {
            return new MyItem(country, "Unknown", "Unknown", "Unknown", "No", "Unknown");
        }
        return item;
    }

    // Returns a list of all MyItem objects
    public static List<MyItem> getItems() {
        return new ArrayList<>(data.values());
    }

    //returns an array of only the countries without their information
    /*public static String[] getCountries(){
            String[] countries = new String[data.size()];
            for(int i=0; i< data.size(); i++) {
                countries[i]=data[i].getCountry();
            }
            return countries;
    }*/
}


