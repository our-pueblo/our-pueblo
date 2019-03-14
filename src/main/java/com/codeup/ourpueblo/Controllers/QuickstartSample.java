package com.codeup.ourpueblo.Controllers;
// [START translate_quickstart]
// Imports the Google Cloud client library
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class QuickstartSample {
    public static void main(String... args) throws Exception {
        // Instantiates a client
        Translate translate = TranslateOptions.getDefaultInstance().getService();

        // The text to translate
        String text = "Accessibility Information & Quick Links\n" +
                "Skip to Main Content\n" +
                "Select Language\u200B▼\n" +
                "GET CONNECTED\n" +
                "GET CONNECTED\n" +
                "  Agendas\n" +
                "  Bidding & Contracting Opportunities\n" +
                "  Boards & Commissions\n" +
                "  City Calendars\n" +
                "  City Council & Staff\n" +
                "  City Manager & Executive Staff\n" +
                "  City News\n" +
                "  Departments Directory\n" +
                "\n" +
                "\n" +
                "  Department Newsletters\n" +
                "  FAQs\n" +
                "  Mobile Apps & Sites\n" +
                "  Online Payments\n" +
                "  Online Registration & Reservation\n" +
                "  Online Services\n" +
                "  Open Data SA\n" +
                "  Social Media Center\n" +
                "RESIDENTS\n" +
                "EMERGENCY SERVICES & INFORMATION\n" +
                "Fire\n" +
                "Police\n" +
                "Street Closures\n" +
                "Traffic\n" +
                "\n" +
                "\n" +
                "EVENTS\n" +
                "City Web Calendar\n" +
                "RECREATION\n" +
                "Alamodome\n" +
                "Art & Culture\n" +
                "Convention Center\n" +
                "Events Calendar\n" +
                "Golf Courses\n" +
                "Parking Downtown\n" +
                "Parks & Recreation\n" +
                "Public Library\n" +
                "SAT International Airport\n" +
                "SA Bikes\n" +
                "SERVICES\n" +
                "Animal Care Services &\n" +
                "Pet Adoptions\n" +
                "Customer Service & 311\n" +
                "City Connect - Online Services\n" +
                "Track Online Service Request\n" +
                "Code Enforcement\n" +
                "Garbage & Other Collections\n" +
                "Metro Health\n" +
                "Neighborhood & Housing Services\n" +
                "Human Services\n" +
                "Development Services\n" +
                "Transportation & Capital Improvements\n" +
                "ONLINE PAYMENTS\n" +
                "CPS Energy\n" +
                "Garage Sale Permits\n" +
                "Online Citation Payments\n" +
                "San Antonio Water System\n" +
                "\n" +
                "\n" +
                "MAPS & STATISTICS\n" +
                "Community Statistics (NowData)\n" +
                "Crime Data & Stats\n" +
                "Health Data & Statistics\n" +
                "Maps\n" +
                "Zoning\n" +
                "CITY GOVERNMENT & DEPARTMENTS\n" +
                "Mayor\n" +
                "City Council\n" +
                "City Manager\n" +
                "City Clerk\n" +
                "All City Departments\n" +
                "\n" +
                "\n" +
                "More Residents Links\n" +
                "VISITORS\n" +
                "ACTIVITIES & ATTRACTIONS\n" +
                "Arts & Culture\n" +
                "City Parks & Facilities\n" +
                "FIESTA - official website\n" +
                "Museums, Galleries & Exhibits\n" +
                "Library\n" +
                "Market Square\n" +
                "Public Art\n" +
                "Theatre\n" +
                "ACCESSIBILITY\n" +
                "Disability Access Information\n" +
                "\n" +
                "\n" +
                "ENTERTAINMENT & CONVENTIONS FACILITIES\n" +
                "Alamodome\n" +
                "Carver Community Cultural Center\n" +
                "Convention Center & Venues\n" +
                "Meetings & Facilities\n" +
                "CITY AT A GLANCE\n" +
                "cafecollege.org\n" +
                "Film Commission\n" +
                "Media Info\n" +
                "Request a Relocation Guide\n" +
                "Request a Visitor Packet\n" +
                "TRANSPORTATION\n" +
                "Airlines & Flight Information\n" +
                "Parking Downtown\n" +
                "Shared Parking\n" +
                "\n" +
                "\n" +
                "EMERGENCY SERVICES\n" +
                "Fire\n" +
                "Police\n" +
                "\n" +
                "\n" +
                "More Visitors Links\n" +
                "BUSINESS\n" +
                "BIDS & PURCHASING\n" +
                "Bidding & Contracting Opportunities\n" +
                "City Contract Management System\n" +
                "Doing Business with the City\n" +
                "San Antonio eProcurement System (SAePS)\n" +
                "BUILDING & DEVELOPMENT\n" +
                "Building Permits Info\n" +
                "Center City Development\n" +
                "Development Services\n" +
                "Economic Development\n" +
                "International Relations\n" +
                "Online City Services\n" +
                "Planning & Community Development\n" +
                "Transportation & Capital Improvements\n" +
                "EMERGENCY SERVICES\n" +
                "Fire\n" +
                "Police\n" +
                "MAPS & STATISTICS\n" +
                "Community Statistics (NowData)\n" +
                "Crime Data & Stats\n" +
                "Health Data & Statistics\n" +
                "San Antonio Demographics\n" +
                "Zoning\n" +
                "CITY GOVERNMENT & DEPARTMENTS\n" +
                "Mayor\n" +
                "City Council\n" +
                "City Manager\n" +
                "City Clerk\n" +
                "All City Departments\n" +
                "\n" +
                "\n" +
                "More Business Links\n" +
                "YOUR GOVERNMENT\n" +
                "ADMINISTRATIVE\n" +
                "All City Departments\n" +
                "City Auditor\n" +
                "City Clerk\n" +
                "City Code\n" +
                "City Holidays\n" +
                "Government\n" +
                "& Public Affairs\n" +
                "Contact the City\n" +
                "Job Openings\n" +
                "Your City Government - Summary\n" +
                "BOARDS & COMMISSIONS\n" +
                "Information\n" +
                "Agendas\n" +
                "\n" +
                "\n" +
                "CITY GOVERNMENT & DEPARTMENTS\n" +
                "Mayor\n" +
                "City Council\n" +
                "City Manager\n" +
                "City Clerk\n" +
                "Military Affairs\n" +
                "All City Departments\n" +
                "ELECTION, ETHICS & LEGAL\n" +
                "City Attorney\n" +
                "Ethics & Lobbyist\n" +
                "Elections & Campaign Finance\n" +
                "\n" +
                "\n" +
                "EMERGENCY SERVICES\n" +
                "Fire\n" +
                "Police\n" +
                "\n" +
                "\n" +
                "ENERGY & GREEN INITIATIVES\n" +
                "Sustainability\n" +
                "Recycling\n" +
                "FINANCE\n" +
                "Grants Monitoring & Administration\n" +
                "Management & Budget Finance\n" +
                "Purchasing\n" +
                "\n" +
                "\n" +
                "MUNICIPAL COURTS\n" +
                "Municipal Court\n" +
                "Online Court Payments\n" +
                "SERVICES\n" +
                "311 City Services\n" +
                "City Connect - Online Services & Payments\n" +
                "OTHER PUBLIC AGENCIES\n" +
                "Other Public Agencies\n" +
                "\n" +
                "\n" +
                "More Government Links...\n" +
                "ESPAÑOL\n" +
                "\t\n" +
                "RON NIRENBERG\n" +
                "MAYOR\n" +
                "\n" +
                "Ron Nirenberg is the mayor of San Antonio, the fastest growing city with the 7th largest population in the United States...\n" +
                "\n" +
                " \n" +
                "MAYOR AND COUNCIL INFORMATION\n" +
                "CITY COUNCIL & COMMITTEE MEETING AGENDAS\n" +
                "FIND YOUR CITY COUNCIL MEMBER\n" +
                "CHARTER REVIEW COMMISSION\n" +
                "SERVICIOS DE TRADUCCIÓN Y AGENDAS\n" +
                "WATCH CITY COUNCIL MEETINGS & B-SESSIONS\n" +
                "ERIK WALSH\n" +
                "CITY MANAGER\n" +
                "\n" +
                "Erik Walsh is the City Manager of San Antonio, the nation’s seventh largest city. As the chief executive officer... \n" +
                "\n" +
                " \n" +
                " \n" +
                "CITY FOCUS\n" +
                "JOB VACANCIES\n" +
                "OUR MISSION AND CORE VALUES\n" +
                "COLLECTIVE BARGAINING\n" +
                " \n" +
                " \n" +
                "TRANSPARENCY IN GOVERNMENT\n" +
                "BIDDING & CONTRACTING\n" +
                "HIGH PROFILE SOLICITATIONS\n" +
                "BUDGET & FINANCE INFORMATION\n" +
                "OPEN DATA SA\n" +
                "OPEN GOVERNMENT - REQUEST DOCUMENTS\n" +
                "PERMITS, INSPECTION AND REVIEW\n" +
                "ZONING MAPS\n" +
                "CITY SPOTLIGHT\n" +
                "YOU’RE INVITED TO THE CITY MANAGER’S 5K - MARCH 31\n" +
                "Register by March 17 to pay only $15 (t-shirt included)\n" +
                "SPEAK UP ON DOCKLESS VEHICLES!\n" +
                "The City of San Antonio is seeking feedback on dockless vehicles. Take our survey and share your input at SASpeakUp.com.\n" +
                "FREE AND OPEN ACCESS TO CITY DATA\n" +
                "Visit Open Data SA where you can view and download available Open Data from the City of San Antonio.\n" +
                "APPLYING FOR EMERGENCY ASSISTANCE MADE EASIER\n" +
                "Use your smart phone or tablet’s built-in camera to upload documents. Access the online application at www.sanantonio.gov/DHSutility.\n" +
                "SASPEAKUP TOWN HALL FOR THE CITY BUDGET\n" +
                "Join us from the comfort of your home on March 19th to learn more about how you can #SpeakUp on the upcoming City budget! Register and we'll call YOU.\n" +
                "311 CITY SERVICES\n" +
                "\n" +
                "Report an issue online or on your smart phone.\n" +
                "\n" +
                "CPS ENERGY OUTAGES\n" +
                "\n" +
                "Report or view current outages online.\n" +
                "\n" +
                "FY19 ADOPTED BUDGET\n" +
                "\n" +
                "FY 2019 Adopted Operating and Capital Budget.\n" +
                "\n" +
                "STREET CLOSURES\n" +
                "\n" +
                "See a list of existing closures that may affect your commute.\n" +
                "\n" +
                " \n" +
                "\n" +
                "\n" +
                "CONNECT WITH THE CITY OF SAN ANTONIO\n" +
                "EMERGENCY INFORMATION\n" +
                "FIRE  POLICE  STREET CLOSURES  EMERGENCY MANAGEMENT\n" +
                " \n" +
                "311 ONLINE CITY SERVICES\n" +
                "\n" +
                "By phone: dial 311 or 210.207.6000\n" +
                "\n" +
                "REPORTS AND MEDIA\n" +
                "CITY CALENDARS\n" +
                "POLICE REPORTS\n" +
                "TVSA NETWORK\n" +
                "FEATURED INFORMATION\n" +
                "ANIMAL CARE SERVICES\n" +
                "BIRTH CERTIFICATES\n" +
                "E-SURPLUS-SA\n" +
                "PARKS & FACILITIES\n" +
                " \n" +
                "LATEST NEWS\n" +
                "Wednesday, March 13, 2019\n" +
                "Mayor Nirenberg Urges Congress to Invest in Transportation...\n" +
                "Tuesday, March 12, 2019\n" +
                "YOU’RE INVITED TO THE 11TH ANNUAL CITY MANAGER’S 5K ON MARCH 31\n" +
                "Tuesday, March 12, 2019\n" +
                "City of San Antonio Makes it Easier than Ever to Participate in...\n" +
                "Friday, March 08, 2019\n" +
                "Councilwoman Rebecca J. Viagran applauds residents for involvement...\n" +
                "More News Releases... \n" +
                "\n" +
                "\n" +
                "Copyright © 2000-2019 City of San Antonio\n" +
                "Office of Equity ADA Compliance Telecommuting Site Map Open Records Contact Us Privacy Policy & Disclaimer";

        // Translates some text into Russian
        Translation translation =
                translate.translate(
                        text,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("es"));


//        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());
    }
}
// [END translate_quickstart]