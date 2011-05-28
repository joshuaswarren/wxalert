package com.creatuity.wxalert;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ListActivity;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class WeatherAlert extends ListActivity implements LocationListener{
    LocationManager location = null;
    Location lastLocation = null;
    String county = null;
    String state = null;
    String postalCode = null;
    String fips = null; 
    ArrayList<String> alertList = new ArrayList<String>();
    ArrayList<Alert> alerts = new ArrayList<Alert>();
    ArrayAdapter<String> adapter = null;
    
    protected void refreshAlerts() {
    	adapter.clear();
    	for (Alert alert: alerts) { 		
    		adapter.add(alert.title);
    	}
    }
    
    private void convertStateToAbbreviation() {
    	if(this.state.length() > 2) {
    		if(this.state.compareTo("alabama") == 0) 
    			this.state = "al";
    		if(this.state.compareTo("alaska") == 0) 
    			this.state = "ak";
    		if(this.state.compareTo("american samoa") == 0) 
    			this.state = "as";
    		if(this.state.compareTo("arizona") == 0) 
    			this.state = "az";
    		if(this.state.compareTo("arkansas") == 0) 
    			this.state = "ar";
    		if(this.state.compareTo("california") == 0) 
    			this.state = "ca";
    		if(this.state.compareTo("colorado") == 0) 
    			this.state = "co";
    		if(this.state.compareTo("connecticut") == 0) 
    			this.state = "ct";
    		if(this.state.compareTo("delaware") == 0) 
    			this.state = "de";
    		if(this.state.compareTo("district of  columbia") == 0) 
    			this.state = "dc";
    		if(this.state.compareTo("federated states of micronesia") == 0) 
    			this.state = "fm";
    		if(this.state.compareTo("florida") == 0) 
    			this.state = "fl";
    		if(this.state.compareTo("georgia") == 0) 
    			this.state = "ga";
    		if(this.state.compareTo("guam") == 0) 
    			this.state = "gu";
    		if(this.state.compareTo("hawaii") == 0) 
    			this.state = "hi";
    		if(this.state.compareTo("idaho") == 0) 
    			this.state = "id";
    		if(this.state.compareTo("illinois") == 0) 
    			this.state = "il";
    		if(this.state.compareTo("indiana") == 0) 
    			this.state = "in";
    		if(this.state.compareTo("iowa") == 0) 
    			this.state = "ia";
    		if(this.state.compareTo("kansas") == 0) 
    			this.state = "ks";
    		if(this.state.compareTo("kentucky") == 0) 
    			this.state = "ky";
    		if(this.state.compareTo("louisiana") == 0) 
    			this.state = "la";
    		if(this.state.compareTo("maine") == 0) 
    			this.state = "me";
    		if(this.state.compareTo("marshall islands") == 0) 
    			this.state = "mh";
    		if(this.state.compareTo("maryland") == 0) 
    			this.state = "md";
    		if(this.state.compareTo("massachusetts") == 0) 
    			this.state = "ma";
    		if(this.state.compareTo("michigan") == 0) 
    			this.state = "mi";
    		if(this.state.compareTo("minnesota") == 0) 
    			this.state = "mn";
    		if(this.state.compareTo("mississippi") == 0) 
    			this.state = "ms";
    		if(this.state.compareTo("missouri") == 0) 
    			this.state = "mo";
    		if(this.state.compareTo("montana") == 0) 
    			this.state = "mt";
    		if(this.state.compareTo("nebraska") == 0) 
    			this.state = "ne";
    		if(this.state.compareTo("nevada") == 0) 
    			this.state = "nv";
    		if(this.state.compareTo("new hampshire") == 0) 
    			this.state = "nh";
    		if(this.state.compareTo("new jersey") == 0) 
    			this.state = "nj";
    		if(this.state.compareTo("new mexico") == 0) 
    			this.state = "nm";
    		if(this.state.compareTo("new york") == 0) 
    			this.state = "ny";
    		if(this.state.compareTo("north carolina") == 0) 
    			this.state = "nc";
    		if(this.state.compareTo("north dakota") == 0) 
    			this.state = "nd";
    		if(this.state.compareTo("north mariana islands") == 0) 
    			this.state = "mp";
    		if(this.state.compareTo("ohio") == 0) 
    			this.state = "oh";
    		if(this.state.compareTo("oklahoma") == 0) 
    			this.state = "ok";
    		if(this.state.compareTo("oregon") == 0) 
    			this.state = "or";
    		if(this.state.compareTo("palau") == 0) 
    			this.state = "pw";
    		if(this.state.compareTo("pennsylvania") == 0) 
    			this.state = "pa";
    		if(this.state.compareTo("puerto rico") == 0) 
    			this.state = "pr";
    		if(this.state.compareTo("rhode island") == 0) 
    			this.state = "ri";
    		if(this.state.compareTo("south carolina") == 0) 
    			this.state = "sc";
    		if(this.state.compareTo("south dakota") == 0) 
    			this.state = "sd";
    		if(this.state.compareTo("tennessee") == 0) 
    			this.state = "tn";
    		if(this.state.compareTo("texas") == 0) 
    			this.state = "tx";    		
    		if(this.state.compareTo("utah") == 0) 
    			this.state = "ut";
    		if(this.state.compareTo("vermont") == 0) 
    			this.state = "vt";
    		if(this.state.compareTo("virgin islands") == 0) 
    			this.state = "vi";
    		if(this.state.compareTo("virginia") == 0) 
    			this.state = "va";
    		if(this.state.compareTo("washington") == 0) 
    			this.state = "wa";
    		if(this.state.compareTo("west virginia") == 0) 
    			this.state = "wv";
    		if(this.state.compareTo("wisconsin") == 0) 
    			this.state = "wi";
    		if(this.state.compareTo("wyoming") == 0) 
    			this.state = "wy";
    	}
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, alertList);
        setListAdapter(adapter);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
            // When clicked, show a toast with the TextView text
            Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                Toast.LENGTH_SHORT).show();
          }
        });

        Log.v("wxalert", "Starting up...");
        location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Iterator<String> providers = location.getAllProviders().iterator();               
                while(providers.hasNext()) {
                    Log.v("wxalert", providers.next());
                }
                
                Criteria criteria = new Criteria();
                criteria.setAccuracy(Criteria.NO_REQUIREMENT);
                criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
                String best = location.getBestProvider(criteria, true);
                Log.v("wxalert", "Best provider: " + best);
                Log.v("wxalert", "Getting last known location");
                lastLocation = location.getLastKnownLocation(best);
                if (lastLocation == null) {
                	Log.v("wxalert", "Last known location is null before call to processLocation");
                }
                Log.v("wxalert", "Got last known location, processing");
                processLocation();	
                Log.v("wxalert", "Last known location processed, requesting location updates.");
                location.requestLocationUpdates(best, 600000, 10, this);

    }
    
    private void determineCurrentFIPS() {
    	try {
    		Log.i("wxalert", "Fetching current FIPS data.");
    		URL text = new URL("http://data.fcc.gov/api/block/find?latitude=" + lastLocation.getLatitude() + "&longitude=" + lastLocation.getLongitude());
    		XmlPullParserFactory parserCreator =
    			XmlPullParserFactory.newInstance();
    		XmlPullParser parser = parserCreator.newPullParser();
    		parser.setInput(text.openStream(), null);
    		int parserEvent = parser.getEventType();
    		while (parserEvent != XmlPullParser.END_DOCUMENT) {
    			switch(parserEvent) {
    			case XmlPullParser.START_TAG:
    				String tag = parser.getName();
    				if(tag.compareTo("County") == 0) {
    					String myFips = parser.getAttributeValue(null, "FIPS");
    					fips = myFips; 
    					Log.i("wxalert", "Your current FIPS is " + fips);
    				}
    				break;
    			}
    			parserEvent = parser.next();
    		}
    		Log.i("wxalert", "Done parsing FIPS data.");
    			} catch (Exception e) {
    		Log.e("wxalert", "Error fetching current FIPS from the FTC");
    		Log.e("wxalert", e.getMessage());
    	}
    }
    
    private void getAllAlerts() {
    	try {
    		Log.i("wxalert", "Fetching current NWS alerts");
    		// URL text = new URL("http://alerts.weather.gov/cap/" + state + ".php?x=1");
    		String text = "http://alerts.weather.gov/cap/" + state + ".php?x=1";
    		AndroidSaxFeedParser parser = new AndroidSaxFeedParser(text); 
    		List<Message> messages = parser.parse();
    		for (Message m : messages) { 
    			Log.i("wxalert", m.getTitle());
    		}
    		Log.i("wxalert", "Done parsing alert data from " + text);    		
    	} catch (Exception e) {
    		Log.e("wxalert", "Error fetching current alerts from NWS from " + "http://alerts.weather.gov/cap/" + state + ".php?x=1");
    		Log.e("wxalert", e.getMessage());
    		alerts.clear();
            Alert newAlert = new Alert();
            newAlert.id = "LOCATION";
        	newAlert.title = "Scanning alerts for " + this.county + " County, " + this.state.toUpperCase(); 
        	newAlert.alerted = true; 
        	alerts.add(newAlert);
        	Alert newAlert2 = new Alert();
            newAlert2.id = "ERROR";
        	newAlert2.title = "The NWS Alert Server Returned an Error."; 
        	newAlert2.alerted = true; 
        	alerts.add(newAlert2);    		
    	}
    }
    
    private void countyChanged() {
		alerts.clear();
        Alert newAlert = new Alert();
        newAlert.id = "LOCATION";
    	newAlert.title = "Scanning alerts for " + this.county + " County, " + this.state.toUpperCase(); 
    	newAlert.alerted = true; 
    	alerts.add(newAlert);
    	refreshAlerts();               
    	determineCurrentFIPS();    	
    	getAllAlerts();
    }
    
    private void processLocation() {
    	if(lastLocation == null) {
    		Log.v("wxalert", "Last known location is null");
    		return; 
    	} else {
    		Log.v("wxalert", "Got valid location - geocoding");
    	}
        Geocoder coder = new Geocoder(this);
        String locInfo = ""; 
        try {
            Iterator<Address> addresses = coder.getFromLocation(lastLocation.getLatitude(), lastLocation.getLongitude(), 3).iterator();
            if (addresses != null) {
                while (addresses.hasNext()) {
                    Address namedLoc = addresses.next();
                    String placeName = namedLoc.getLocality();
                    String featureName = namedLoc.getFeatureName();
                    String country = namedLoc.getCountryName();
                    String county = namedLoc.getSubAdminArea();
                    String postalCode = namedLoc.getPostalCode();
                    String road = namedLoc.getThoroughfare();
                    String state = namedLoc.getAdminArea();
                    state = state.toLowerCase();
                    if (state != null && state.length() > 0) { 
                    	this.state = state; 
                    	convertStateToAbbreviation();
                    }
                    if (postalCode != null && postalCode.length() > 0) {
                    	this.postalCode = postalCode;
                    	Log.i("wxalert", postalCode);
                    }
                    if (county != null && county.length() > 0) {
                    	String lastCounty = this.county;
                    	this.county = county;
                    	if(lastCounty != this.county) {
                    		// county has changed, update location
                    		countyChanged();                    		
                    	}
                    	break; // stop once we find a county
                    }
                    locInfo += String.format("\n[%s][%s][%s][%s]", placeName, featureName, road, country);
                    int addIdx = namedLoc.getMaxAddressLineIndex();
                    for (int idx = 0; idx <= addIdx; idx++){
                        String addLine = namedLoc.getAddressLine(idx);
                        locInfo += String.format("\nLine %d: %s", idx, addLine);
                    }
                }
            }
        } catch (IOException e) {
            Log.e("wxalert", "Failed to get address", e);
        }
        Log.v("wxalert", "You are located in " + this.county + " - " + this.postalCode);
    }
    
    public void onLocationChanged(Location location) {
        String locInfo = String.format("Current loc = (%f, %f) @ (%f meters up)", location.getLatitude(), location.getLongitude(), location.getAltitude() );
        if (lastLocation != null) {
            float distance = location.distanceTo(lastLocation);
            locInfo += String.format("\n Distance from last = %f meters", distance);
            Log.i("wxalert", locInfo);
        }
        lastLocation = location;
        processLocation();	
    }

    public void onProviderDisabled(String provider) {
        Log.v("wxalert", "Provider disabled "+ provider);      
    }

    public void onProviderEnabled(String provider) {
        Log.v("wxalert", "Provider enabled "+ provider);        
    }

    
    public void onStatusChanged(String provider, int status, Bundle extras) {
        int satellites = extras.getInt("satellites", -1);       
        String statusInfo = String.format("Provider: %s, satellites: %d", provider, satellites);
        Log.v("wxalert", statusInfo);        
    }

}
