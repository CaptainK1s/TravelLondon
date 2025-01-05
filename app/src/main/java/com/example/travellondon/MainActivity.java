package com.example.travellondon;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupTransportation;
    private Spinner spinnerDestinations;
    private Button btnClear, btnSubmit;
    private TextView tvSelectedDestination;

    // Descriptions for each destination
    private Map<String, String> boatDescriptions;
    private Map<String, String> trainDescriptions;
    private Map<String, String> planeDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing Views
        radioGroupTransportation = findViewById(R.id.radioGroupTransportation);
        spinnerDestinations = findViewById(R.id.spinnerDestinations);
        btnClear = findViewById(R.id.btnClear);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvSelectedDestination = findViewById(R.id.tvSelectedDestination);

        initDescriptions();

        //  RadioGroup selection
        radioGroupTransportation.setOnCheckedChangeListener((group, checkedId) -> updateSpinnerOptions(checkedId));

        //  Clear button click
        btnClear.setOnClickListener(v -> {
            radioGroupTransportation.clearCheck();
            spinnerDestinations.setAdapter(null);
            tvSelectedDestination.setText("Selected Destination Info will appear here");
        });

        //  Submit button click
        btnSubmit.setOnClickListener(v -> {
            String selectedDestination = spinnerDestinations.getSelectedItem() != null ? spinnerDestinations.getSelectedItem().toString() : null;
            if (selectedDestination != null) {
                String description = getDestinationDescription(selectedDestination);
                tvSelectedDestination.setText("Selected destination: " + selectedDestination + "\nDescription: " + description);
            } else {
                tvSelectedDestination.setText("Please select a destination.");
            }
        });
    }

    // Enter the descriptions for each city
    private void initDescriptions() {
        boatDescriptions = new HashMap<>();
        boatDescriptions.put("Embankment", "Embankment is a scenic and popular location along the River Thames in central London, offering stunning views of the city. Located near key landmarks like the London Eye and Big Ben, it is known for its riverside walks, historic piers, and cultural attractions. Visitors can explore nearby sites such as the National Theatre and the Southbank Centre, both of which are integral parts of London’s cultural life. Embankment also provides easy access to various boat tours and riverside dining spots, making it an ideal destination for both tourists and locals seeking a relaxing stroll with great views of iconic London landmarks.");
        boatDescriptions.put("Bankside", "Bankside is a vibrant riverside area in London, located just south of the River Thames. Known for its rich cultural offerings, Bankside is home to some of London's most famous attractions, such as the Tate Modern, Shakespeare’s Globe Theatre, and the Millennium Bridge. The area has undergone significant regeneration in recent years, transforming into a lively neighborhood filled with art galleries, restaurants, cafes, and bars. Visitors can enjoy a leisurely walk along the Thames Path or take in the sights and sounds of this bustling cultural hub, making it a must-visit destination for art lovers and tourists alike.");
        boatDescriptions.put("Canary Wharf", "Canary Wharf is a major business and financial district located on the Isle of Dogs in East London. Known for its impressive skyline dominated by towering skyscrapers, this vibrant area is home to global banks, financial institutions, and a wide range of businesses. Beyond its corporate significance, Canary Wharf also offers a variety of high-end shopping centers, restaurants, bars, and cultural spaces, making it a sought-after destination for both work and leisure. Visitors can explore its beautiful riverside walkways, green spaces, and iconic buildings like One Canada Square, while enjoying the dynamic atmosphere of one of London's most modern districts.");

        trainDescriptions = new HashMap<>();
        trainDescriptions.put("Amsterdam", "Amsterdam, the capital of the Netherlands, is known for its picturesque canals, rich history, and vibrant cultural scene. The city is famous for its museums, including the Van Gogh Museum, the Rijksmuseum, and the Anne Frank House, making it a major destination for art lovers and history enthusiasts. Visitors can enjoy cycling through its charming streets, exploring its historic architecture, or relaxing in its cafes. Amsterdam is also known for its lively nightlife, with a variety of bars, restaurants, and clubs. The city offers a perfect blend of old-world charm and modern living, making it a popular spot for tourists and locals alike. London to Amsterdam via train is about 4 hours on the Eurostar.");
        trainDescriptions.put("Edinburgh", "Edinburgh, the capital of Scotland, is a city steeped in history and culture. It’s known for its stunning architecture, including the historic Edinburgh Castle, perched on a volcanic rock, and the Royal Mile, which stretches through the heart of the Old Town. The city is also home to a rich literary tradition, being the birthplace of authors like Sir Walter Scott and J.K. Rowling. Edinburgh’s festivals, including the Edinburgh Festival Fringe, draw thousands of visitors each year, making it a hub for art, music, and theater. Whether you're exploring its cobbled streets or hiking up Arthur's Seat for panoramic views, Edinburgh offers a blend of natural beauty and historical significance. The train journey is about 4.5 hours.");
        trainDescriptions.put("Manchester", "Manchester, a bustling city in the north of England, is renowned for its rich industrial heritage, vibrant music scene, and sporting culture. The city is home to iconic landmarks such as Old Trafford, the stadium of Manchester United, and a thriving arts scene, with museums like the Manchester Museum and the Museum of Science and Industry. Manchester is also famous for its shopping districts, nightlife, and diverse culinary scene. The city's cultural legacy extends from its industrial roots to its current status as a hub for technology, music, and fashion. With its friendly atmosphere and dynamic energy, Manchester is a city that offers something for everyone. Only 2 hours train!");

        planeDescriptions = new HashMap<>();
        planeDescriptions.put("Los Angeles", "Los Angeles, often referred to as the \"City of Angels,\" is one of the most iconic cities in the United States and a global center for entertainment, culture, and tourism. Known for Hollywood, the birthplace of the film industry, LA is also famous for its stunning beaches, vibrant nightlife, world-class museums, and diverse neighborhoods. Visitors can explore the Walk of Fame, experience the thrill of Universal Studios, and take in the view from Griffith Observatory. Los Angeles is also a hub for technology, fashion, and innovation, making it a melting pot of creativity and opportunity. Direct flight will take 10 hours.");
        planeDescriptions.put("Perth", "Perth is the capital of Western Australia, located on the Indian Ocean. Known for its beautiful beaches, relaxed atmosphere, and high quality of life, Perth is one of the most isolated major cities in the world. The city offers a mix of urban sophistication and natural beauty, with stunning parks, beaches, and vineyards. Kings Park, one of the largest inner-city parks in the world, provides spectacular views of the city skyline, while the Swan River offers opportunities for water activities. Perth is also a gateway to exploring the nearby Margaret River region, known for its wineries and scenic landscapes. London to Perth flight time is 17 hours.");
        planeDescriptions.put("Dubai", "Dubai is a global metropolis located in the United Arab Emirates (UAE), known for its ultramodern architecture, luxury shopping, and vibrant nightlife. Famous landmarks like the Burj Khalifa, the tallest building in the world, and the Palm Jumeirah, an artificial archipelago, define the city’s futuristic skyline. Dubai also offers a mix of desert adventures, such as dune bashing, camel rides, and luxury resorts. It’s a city where modernity blends with traditional Arab culture, and visitors can enjoy a variety of cultural attractions, shopping malls, and world-class dining experiences. Dubai is renowned for its extravagant lifestyle and serves as a major hub for business and tourism in the Middle East. Direct flight will take 7 hours.");
    }

    // Update Spinner options based on the transportation selection
    private void updateSpinnerOptions(int checkedId) {
        String[] destinations = {};
        if (checkedId == R.id.radioBoat) {
            destinations = new String[]{"Embankment", "Bankside", "Canary Wharf"};
        } else if (checkedId == R.id.radioTrain) {
            destinations = new String[]{"Amsterdam", "Edinburgh", "Manchester"};
        } else if (checkedId == R.id.radioPlane) {
            destinations = new String[]{"Los Angeles", "Perth", "Dubai"};
        }

        // Set the adapter for the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, destinations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestinations.setAdapter(adapter);
    }

    // Get description for the selected destination
    private String getDestinationDescription(String destination) {
        if (radioGroupTransportation.getCheckedRadioButtonId() == R.id.radioBoat) {
            return boatDescriptions.get(destination);
        } else if (radioGroupTransportation.getCheckedRadioButtonId() == R.id.radioTrain) {
            return trainDescriptions.get(destination);
        } else if (radioGroupTransportation.getCheckedRadioButtonId() == R.id.radioPlane) {
            return planeDescriptions.get(destination);
        }
        return "No description available.";
    }
}
