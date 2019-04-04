# project-04
Server with Improved GUI and Multimodal Client
The server UI is made using fxml which has dependencies on Jar file JFoenix and Javafx. We have provided both the jars, which are required for the correct functioning of the program. 
To install JARS on InteiijIdea:

1. Click File from the toolbar
2. Project Structure (CTRL + SHIFT + ALT + S on Windows/Linux, ⌘ + ; on Mac OS X)
3. Select Modules at the left panel
4. Dependencies tab
5. '+' → JARs or directories ( Add jfoenix-8.0.2.jar and jfxswt.jar)

To install JARS on Eclipse:

Eclipse -> Preferences -> Java -> Build Path -> User Libraries -> New(Name it) -> Add external Jars


Project-04: POINTS TO CONSIDER
1. IMPROVED SERVER SIDE UI: /res/serverUIFinal.png
2. DATA GENERATED FROM ONE SERVER AND PLOTTED
3. THE X-AXIS WAS TAKEN AS PLEASURE AND Y-AXIS AS AROUSAL, WE TRANSFORMED THE PIXEL COORDINATES TO GRAPH COORDINATES
USING A FORMULA.
4. IMAGE GENEREATED IS OF PIXEL 50 * 50
5. WE GENERATED GOOD IMAGES : HIGH AGREE, HIGH CONCENTRATE, HIGH THINKING
6. WE GENERATED BAD IMAGES : HIGH DISAGREE, HIGH FRUSTATE, HIGH UNSURE
7. SINCE IT TOOK TIME TO GENERATE DATA, AND SINCE TRAINING DATA WAS REQUIRED TO BE 20,000 IMAGES
WE YET HAVE NOT IMPLEMENTED THE NEURAL NETWORK PART.
9. IN THE IMAGES FOLDER YOU CAN SEE IMAGES LABELLED AS GOODIMAGES AND BADIMAGES AND THEIR CORRESPONDING GRAPHS. 
8. WE WROTE A PYTHON SCRIPT FOR NEURAL NET TRAINING, HOWEVER WE ARE YET GENERATING DATA AND WILL TAKE SOME TIME TO IMPLEMENT IT.
