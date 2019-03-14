package com.codeup.ourpueblo.Controllers;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translate.TranslateOption;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;


public class QuickstartSample {

    public static void main(String... args) throws Exception {
        // Instantiates a client
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        // Enter sample text to be translated
        String text = "\"COMMUNITY POLICING OVERVIEW\n" +
                "\n" +
                "The San Antonio Police Department has embraced Community Policing for many decades, through its Community Services and School Services Programs, Crime Prevention Programs (Neighborhood Watch, National Night Out), Store Fronts, Decentralized Patrol Substations, and the Downtown Foot and Bicycle Patrol Unit. In 1995 the Department created a special Community Policing Unit, the San Antonio Fear Free Environment Unit (SAFFE) which links closely with community involvement programs, such as Cellular on Patrol (initiated in 1993) and the Citizen Police Academy (initiated 1994).\n" +
                "\n" +
                "WHAT IS COMMUNITY POLICING?\n" +
                "\n" +
                "Community Policing is a collaborative effort between a police department and community that identifies problems of crime and disorder and involves all elements of the community in the search for solutions to these problems. It is founded on close, mutually beneficial ties between police and community members. At the center of community policing are three essential and complementary core components:\n" +
                "\n" +
                "Partnerships between the police and the community.\n" +
                "Problem Solving as a method to identify and solve problems of concern to the community\n" +
                "Change Management within the police organization to accommodate increased community involvement.\n" +
                "WHAT IS P.O.P.?\n" +
                "\n" +
                "P.O.P., or Problem-Oriented Policing is the tactic used to produce long-term solutions to problems of crime or decay in communities. Police, residents, and other agencies or organizations work together to identify and find the causes for neighborhood crime problems, then develop responses to the problems based on the problems' causes. Responses are NOT cookie-cutter, one-response-fits-all. Responses are not 100% police responses. In most cases, the responses developed through P.O.P. are joint police-community actions, which also involve participation by agencies such as parks, code enforcement, youth services, solid waste, and others.\n" +
                "\n" +
                "P.O.P. in San Antonio and in most police departments throughout the U.S., Canada and the U.K. uses the S.A.R.A. (or SARAM) method for problem solving:\n" +
                "\n" +
                "S = Scanning : Identify and describe the PROBLEM.\n" +
                "A = Analyze : How much of a problem is it? When is it a problem? Who is affected? What are the problem's causes?\n" +
                "R = Response : Based on the two steps above, devise an appropriate response to the problem. There may be several responses. Responses may be done over a long period of time.\n" +
                "A = Assess : Evaluate the results of the responses. If the results are not satisfactory, re-analyze the problem, or try another response.\n" +
                "M = Maintenance: Problem solving is only successful if it produces a long-term solution. A POP response may have short-term positive results, but can these results be maintained over a longer term (6-month? 1-2 years?) without constant intervention by police, residents and/or other agencies? In other words, \"Has the Problem been Solved?\"\n" +
                "\n" +
                "P.O.P. is being used by police and residents to produce long-term solutions to persistent crime problems in communities throughout San Antonio, resulting in reductions not only in crime rates, but also in the fear of crime.\"";

        // Translates some text into Spanish
        Translation translation =
                translate.translate(
                        text,
                        TranslateOption.sourceLanguage("en"),
                        TranslateOption.targetLanguage("es"));

        // Print for comparison
        System.out.printf("Text: %s%n", text);
        System.out.printf("Translation: %s%n", translation.getTranslatedText());
    }
}
// [END translate_quickstart]