package developer.exam.live.vi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionDataItemFactory {

    public static List<QuestionDataItem> makeGenres() {
        ArrayList<QuestionDataItem> questionList = new ArrayList<>();

        questionList.add(new QuestionDataItem("What is a coronavirus?\n", coronavirus()));
        questionList.add(new QuestionDataItem("What is COVID-19?\n", covid19()));
        questionList.add(new QuestionDataItem(question[0], answers0()));
        questionList.add(new QuestionDataItem(question[1], answers1()));
        questionList.add(new QuestionDataItem(question[2], answers2()));
        questionList.add(new QuestionDataItem(question[3], answers3()));
        questionList.add(new QuestionDataItem(question[4], answers4()));
        questionList.add(new QuestionDataItem(question[5], answers5()));
        questionList.add(new QuestionDataItem(question[6], answers6()));
        questionList.add(new QuestionDataItem(question[7], answers7()));
        questionList.add(new QuestionDataItem(question[8], answers8()));
        questionList.add(new QuestionDataItem(question[9], answers9()));
        questionList.add(new QuestionDataItem(question[10], answers10()));
        questionList.add(new QuestionDataItem(question[11], answers11()));
        questionList.add(new QuestionDataItem(question[12], answers12()));
        questionList.add(new QuestionDataItem(question[13], answers13()));
        questionList.add(new QuestionDataItem(question[14], answers14()));
        questionList.add(new QuestionDataItem(question[15], answers15()));
        questionList.add(new QuestionDataItem(question[16], answers16()));
        questionList.add(new QuestionDataItem(question[17], answers17()));
        questionList.add(new QuestionDataItem(question[18], answers18()));
        questionList.add(new QuestionDataItem(question[19], answers19()));
        questionList.add(new QuestionDataItem(question[20], answers20()));

        return questionList;
    }


    private static String[] question  = {"What are the symptoms of COVID-19?\n",
            "How does COVID-19 spread?\n",
    "Can the virus that causes COVID-19 be transmitted through the air?\n",
            "Can CoVID-19 be caught from a person who has no symptoms?\n",
    "Can I catch COVID-19 from the feces of someone with the disease?\n",
    "What can I do to protect myself and prevent the spread of disease?\n",
    "How likely am I to catch COVID-19?\n",
    "Should I worry about COVID-19?\n",
            "Who is at risk of developing severe illness?\n",
    "Are antibiotics effective in preventing or treating the COVID-19?\n",
    "Are there any medicines or therapies that can prevent or cure COVID-19?\n",
    "Is there a vaccine, drug or treatment for COVID-19?\n",
    "Is COVID-19 the same as SARS?\n",
    "Should I wear a mask to protect myself?\n",
    "How to put on, use, take off and dispose of a mask?\n",
    "How long is the incubation period for COVID-19?\n",
    "Can humans become infected with the COVID-19 from an animal source?\n",
    "Can I catch COVID-19 from my pet?\n",
    "How long does the virus survive on surfaces?\n",
    "Is it safe to receive a package from any area where COVID-19 has been reported?\n",
    "Is there anything I should not do?\n"};

    private static String []answers = {"The most common symptoms of COVID-19 are fever, tiredness, and dry cough. Some patients may have aches and pains, nasal congestion, runny " +
            "nose, sore throat or diarrhea. These symptoms are usually mild and begin gradually. Some people become infected but don’t develop any symptoms and don't feel unwell. " +
            "Most people (about 80%) recover from the disease without needing special treatment. Around 1 out of every 6 people who gets COVID-19 becomes seriously ill and develops " +
            "difficulty breathing. Older people, and those with underlying medical problems like high blood pressure, heart problems or diabetes, are more likely to develop " +
            "serious illness. People with fever, cough and difficulty breathing should seek medical attention.\n" +
            "\n"

            ,"People can catch COVID-19 from others who have the virus. The disease can spread from person to person through small droplets from the nose or mouth which are " +
            "spread when a person with COVID-19 coughs or exhales. These droplets land on objects and surfaces around the person. Other people then catch COVID-19 by touching " +
            "these objects or surfaces, then touching their eyes, nose or mouth. People can also catch COVID-19 if they breathe in droplets from a person with COVID-19 who coughs " +
            "out or exhales droplets. This is why it is important to stay more than 1 meter (3 feet) away from a person who is sick.\n" +
            "\n" +
            "WHO is assessing ongoing research on the ways COVID-19 is spread and will continue to share updated findings.\n"


            , "Studies to date suggest that the virus that causes COVID-19 is mainly transmitted through contact with respiratory droplets rather than through the air.  " +
            "See previous answer on “How does COVID-19 spread?\n"


            , "The main way the disease spreads is through respiratory droplets expelled by someone who is coughing. The risk of catching COVID-19 from someone with no " +
            "symptoms at all is very low. However, many people with COVID-19 experience only mild symptoms. This is particularly true at the early stages of the disease. " +
            "It is therefore possible to catch COVID-19 from someone who has, for example, just a mild cough and does not feel ill.  WHO is assessing ongoing research on " +
            "the period of transmission of COVID-19 and will continue to share updated findings.\n"


            , "The risk of catching COVID-19 from the feces of an infected person appears to be low. While initial investigations suggest the virus may be present in feces " +
            "in some cases, spread through this route is not a main feature of the outbreak. WHO is assessing ongoing research on the ways COVID-19 is spread and will continue " +
            "to share new findings. Because this is a risk, however, it is another reason to clean hands regularly, after using the bathroom and before eating.\n"


            , "Protection measures for everyone\n" + "Stay aware of the latest information on the COVID-19 outbreak, available on the WHO website and through your national " +
            "and local public health authority. Many countries around the world have seen cases of COVID-19 and several have seen outbreaks. Authorities in China and some " +
            "other countries have succeeded in slowing or stopping their outbreaks. However, the situation is unpredictable so check regularly for the latest news.\n" +
            "\n" +
            "You can reduce your chances of being infected or spreading COVID-19 by taking some simple precautions:\n" +
            "\n" +
            "Regularly and thoroughly clean your hands with an alcohol-based hand rub or wash them with soap and water.\n" +
            "Why? Washing your hands with soap and water or using alcohol-based hand rub kills viruses that may be on your hands.\n" +
            "Maintain at least 1 metre (3 feet) distance between yourself and anyone who is coughing or sneezing.\n" +
            "Why? When someone coughs or sneezes they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the " +
            "droplets, including the COVID-19 virus if the person coughing has the disease.\n" +
            "Avoid touching eyes, nose and mouth.\n" +
            "Why? Hands touch many surfaces and can pick up viruses. Once contaminated, hands can transfer the virus to your eyes, nose or mouth. From there, the virus can enter" +
            " your body and can make you sick.\n" +
            "Make sure you, and the people around you, follow good respiratory hygiene. This means covering your mouth and nose with your bent elbow or tissue when " +
            "you cough or sneeze. Then dispose of the used tissue immediately.\n" +
            "Why? Droplets spread virus. By following good respiratory hygiene you protect the people around you from viruses such as cold, flu and COVID-19.\n" +
            "Stay home if you feel unwell. If you have a fever, cough and difficulty breathing, seek medical attention and call in advance. Follow the directions of " +
            "your local health authority.\n" +
            "Why? National and local authorities will have the most up to date information on the situation in your area. Calling in advance will allow your health " +
            "care provider to quickly direct you to the right health facility. This will also protect you and help prevent spread of viruses and other infections.\n" +
            "Keep up to date on the latest COVID-19 hotspots (cities or local areas where COVID-19 is spreading widely). If possible, avoid traveling to places  – especially " +
            "if you are an older person or have diabetes, heart or lung disease.\n" +
            "Why? You have a higher chance of catching COVID-19 in one of these areas."+"\n"+"Protection measures for persons who are in or have recently visited (past 14 days) " +
            "areas where COVID-19 is spreading\n" +
            "Follow the guidance outlined above (Protection measures for everyone)\n" +
            "Self-isolate by staying at home if you begin to feel unwell, even with mild symptoms such as headache, low grade fever (37.3 C or above) and slight runny nose, " +
            "until you recover. If it is essential for you to have someone bring you supplies or to go out, e.g. to buy food, then wear a mask to avoid infecting other people.\n" +
            "Why? Avoiding contact with others and visits to medical facilities will allow these facilities to operate more effectively and help protect you and others " +
            "from possible COVID-19 and other viruses.\n" +
            "If you develop fever, cough and difficulty breathing, seek medical advice promptly as this may be due to a respiratory infection or other serious condition. " +
            "Call in advance and tell your provider of any recent travel or contact with travelers.\n" +
            "Why? Calling in advance will allow your health care provider to quickly direct you to the right health facility. This will also help to prevent possible " +
            "spread of COVID-19 and other viruses\n"


            , "The risk depends on where you  are - and more specifically, whether there is a COVID-19 outbreak unfolding there.\n" +
            "\n" +
            "For most people in most locations the risk of catching COVID-19 is still low. However, there are now places around the world (cities or areas) where the disease " +
            "is spreading. For people living in, or visiting, these areas the risk of catching COVID-19 is higher. Governments and health authorities are taking vigorous action " +
            "every time a new case of COVID-19 is identified. Be sure to comply with any local restrictions on travel, movement or large gatherings. Cooperating with disease " +
            "control efforts will reduce your risk of catching or spreading COVID-19.\n" +
            "\n" +
            "COVID-19 outbreaks can be contained and transmission stopped, as has been shown in China and some other countries. Unfortunately, new outbreaks " +
            "can emerge rapidly. It’s important to be aware of the situation where you are or intend to go. WHO publishes daily updates on the COVID-19 situation worldwide.\n"


            , "Illness due to COVID-19 infection is generally mild, especially for children and young adults. However, it can cause serious illness: about 1 in every 5 people " +
            "who catch it need hospital care. It is therefore quite normal for people to worry about how the COVID-19 outbreak will affect them and their loved ones.\n" +
            "\n" +
            "We can channel our concerns into actions to protect ourselves, our loved ones and our communities. First and foremost among these actions is regular and " +
            "thorough hand-washing and good respiratory hygiene. Secondly, keep informed and follow the advice of the local health authorities including any restrictions put in place on travel, movement and gatherings.",
    "While we are still learning about how COVID-2019 affects people, older persons and persons with pre-existing medical conditions (such as high blood pressure, heart " +
            "disease, lung disease, cancer or diabetes)  appear to develop serious illness more often than others. \n"


            , "No. Antibiotics do not work against viruses, they only work on bacterial infections. COVID-19 is caused by a virus, so antibiotics do not work. Antibiotics " +
            "should not be used as a means of prevention or treatment of COVID-19. They should only be used as directed by a physician to treat a bacterial infection. \n"


            , "While some western, traditional or home remedies may provide comfort and alleviate symptoms of COVID-19, there is no evidence that current medicine can prevent or " +
            "cure the disease. WHO does not recommend self-medication with any medicines, including antibiotics, as a prevention or cure for COVID-19. However, there are several " +
            "ongoing clinical trials that include both western and traditional medicines. WHO will continue to provide updated information as soon as clinical findings are available.\n"


            , "Not yet. To date, there is no vaccine and no specific antiviral medicine to prevent or treat COVID-2019. However, those affected should receive care to relieve " +
            "symptoms. People with serious illness should be hospitalized. Most patients recover thanks to supportive care.\n" +
            "\n" +
            "Possible vaccines and some specific drug treatments are under investigation. They are being tested through clinical trials. WHO is coordinating efforts to " +
            "develop vaccines and medicines to prevent and treat COVID-19.\n" +
            "\n" +
            "The most effective ways to protect yourself and others against COVID-19 are to frequently clean your hands, cover your cough with the bend of elbow or tissue, " +
            "and maintain a distance of at least 1 meter (3 feet) from people who are coughing or sneezing.",
    "No. The virus that causes COVID-19 and the one that caused the outbreak of Severe Acute Respiratory Syndrome (SARS) in 2003 are related to each other genetically, " +
            "but the diseases they cause are quite different.\n" +
            "\n" +
            "SARS was more deadly but much less infectious than COVID-19. There have been no outbreaks of SARS anywhere in the world since 2003.\n"


            , "Only wear a mask if you are ill with COVID-19 symptoms (especially coughing) or looking after someone who may have COVID-19. Disposable face mask can only be " +
            "used once. If you are not ill or looking after someone who is ill then you are wasting a mask. There is a world-wide shortage of masks, so WHO urges people to use " +
            "masks wisely.\n" +
            "\n" +
            "WHO advises rational use of medical masks to avoid unnecessary wastage of precious resources and mis-use of masks  (see Advice on the use of masks).\n" +
            "\n" +
            "The most effective ways to protect yourself and others against COVID-19 are to frequently clean your hands, cover your cough with the bend of elbow or tissue " +
            "and maintain a distance of at least 1 meter (3 feet) from people who are coughing or sneezing.\n"


            , "Remember, a mask should only be used by health workers, care takers, and individuals with respiratory symptoms, such as fever and cough.\n" +
            "Before touching the mask, clean hands with an alcohol-based hand rub or soap and water\n" +
            "Take the mask and inspect it for tears or holes.\n" +
            "Orient which side is the top side (where the metal strip is).\n" +
            "Ensure the proper side of the mask faces outwards (the coloured side).\n" +
            "Place the mask to your face. Pinch the metal strip or stiff edge of the mask so it moulds to the shape of your nose.\n" +
            "Pull down the mask’s bottom so it covers your mouth and your chin.\n" +
            "After use, take off the mask; remove the elastic loops from behind the ears while keeping the mask away from your face and clothes, to avoid touching potentially " +
            "contaminated surfaces of the mask.\n" +
            "Discard the mask in a closed bin immediately after use.\n" +
            "Perform hand hygiene after touching or discarding the mask – Use alcohol-based hand rub or, if visibly soiled, wash your hands with soap and water.",
            "The “incubation period” means the time between catching the virus and beginning to have symptoms of the disease. Most estimates of the incubation period for " +
                    "COVID-19 range from 1-14 days, most commonly around five days. These estimates will be updated as more data become available.\n"


            , "Coronaviruses are a large family of viruses that are common in animals. Occasionally, people get infected with these viruses which may then spread to other people. " +
            "For example, SARS-CoV was associated with civet cats and MERS-CoV is transmitted by dromedary camels. Possible animal sources of COVID-19 have not yet been " +
            "confirmed.  \n" +
            "\n" +
            "To protect yourself, such as when visiting live animal markets, avoid direct contact with animals and surfaces in contact with animals. Ensure good food safety " +
            "practices at all times. Handle raw meat, milk or animal organs with care to avoid contamination of uncooked foods and avoid consuming raw or undercooked animal " +
            "products.\n"


            , "While there has been one instance of a dog being infected in Hong Kong, to date, there is no evidence that a dog, cat or any pet can transmit COVID-19. " +
            "COVID-19 is mainly spread through droplets produced when an infected person coughs, sneezes, or speaks. To protect yourself, clean your hands frequently and " +
            "thoroughly. \n" +
                    "\n" +
                    "WHO continues to monitor the latest research on this and other COVID-19 topics and will update as new findings are available.\n"


            , "It is not certain how long the virus that causes COVID-19 survives on surfaces, but it seems to behave like other coronaviruses. Studies suggest that coronaviruses " +
            "(including preliminary information on the COVID-19 virus) may persist on surfaces for a few hours or up to several days. This may vary under different conditions " +
            "(e.g. type of surface, temperature or humidity of the environment).\n" +
            "\n" +
            "If you think a surface may be infected, clean it with simple disinfectant to kill the virus and protect yourself and others. Clean your hands with an alcohol-based " +
            "hand rub or wash them with soap and water. Avoid touching your eyes, mouth, or nose.\n"


            , "Yes. The likelihood of an infected person contaminating commercial goods is low and the risk of catching the virus that causes COVID-19 from a package that has " +
            "been moved, travelled, and exposed to different conditions and temperature is also low. \n"


            , "The following measures ARE NOT effective against COVID-2019 and can be harmful:\n" +
            "\n" +
            "Smoking\n" +
            "Wearing multiple masks\n" +
            "Taking antibiotics (See question 10 \"Are there any medicines of therapies that can prevent or cure COVID-19?\")\n" +
            "In any case, if you have fever, cough and difficulty breathing seek medical care early to reduce the risk of developing a more severe infection and be sure to " +
            "share your recent travel history with your health care provider.\n"};


    public static List<AnswerDataItem> coronavirus() {
        AnswerDataItem corona = new AnswerDataItem("Coronaviruses are a large family of viruses which may cause illness in animals or humans. " +
                "In humans, several coronaviruses are known to cause respiratory infections ranging from the common cold to more severe diseases " +
                "such as Middle East Respiratory Syndrome (MERS) and Severe Acute Respiratory Syndrome (SARS). The most recently discovered coronavirus " +
                "causes coronavirus disease COVID-19.\n");
        return Arrays.asList(corona);
    }

    public static List<AnswerDataItem> covid19() {
        AnswerDataItem covid19 = new AnswerDataItem("COVID-19 is the infectious disease caused by the most recently discovered coronavirus. " +
                "This new virus and disease were unknown before the outbreak began in Wuhan, China, in December 2019.\n");

        return Arrays.asList(covid19);
    }

    public static List<AnswerDataItem> answers0() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[0]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers1() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[1]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers2() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[2]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers3() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[3]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers4() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[4]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers5() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[5]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers6() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[6]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers7() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[7]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers8() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[8]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers9(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[9]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers10(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[10]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers11(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[11]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers12(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[12]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers13(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[13]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers14(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[14]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers15(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[15]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers16(){
        AnswerDataItem covid19 = new AnswerDataItem(answers[16]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers17() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[17]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers18() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[18]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers19() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[19]);

        return Arrays.asList(covid19);
    }
    public static List<AnswerDataItem> answers20() {
        AnswerDataItem covid19 = new AnswerDataItem(answers[20]);

        return Arrays.asList(covid19);
    }

}
