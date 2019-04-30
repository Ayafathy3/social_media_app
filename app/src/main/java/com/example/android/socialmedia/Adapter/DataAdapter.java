package com.example.android.socialmedia.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.socialmedia.R;
import com.example.android.socialmedia.classes.Data;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataHolder> {
    ArrayList<Data> arrayList;
    Context context;
    recyclerListener listener;
    private Bitmap bitmap;
    private ImageView imageView;

    public DataAdapter(Context context) {

        this.context = context;
    }


    public void setArrayListAndroid(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;

        arrayList.add(new Data("https://classroom.udacity.com/courses/ud834", R.drawable.dataandroid2, "Android Basics: User Interface", "Learn the basics of Android and Java programming, and take the first step on your journey to becoming an Android developer! "));
        arrayList.add(new Data("https://classroom.udacity.com/courses/ud836", R.drawable.dataandroid, "Android Basics: User Input", "This course is designed for students who are new to programming, and want to learn how to build Android apps."));
        arrayList.add(new Data("https://eg.udacity.com/course/android-basics-multiscreen-apps--ud839", R.drawable.dataandroid3, "Android Basics: Multiscreen Apps", "This course is designed for students who have completed the Android for Beginners course."));
        arrayList.add(new Data("https://eg.udacity.com/course/android-basics-networking--ud843", R.drawable.dataandroid4, "Android Basics: Networking", "Learning anything new can be tough. We will walk you through the process of making Android apps, but to get the most out of this course,"));
        arrayList.add(new Data("https://eg.udacity.com/course/android-basics-data-storage--ud845", R.drawable.dataandroid5, "Android Basics: Data Storage", "In this course, you will learn about the importance of data persistence when building an Android app. We'll introduce you to the fundamentals of SQL,"));
        arrayList.add(new Data("https://eg.udacity.com/course/advanced-android-app-development--ud855", R.drawable.dataandroid6, "Advanced Android App Development", "In this course, you will learn how to make your app production-ready by developing a variety of different sample apps, each designed to showcase advanced capabilities of the Android platform, including fragments, widgets, media playback, and testing."));
        arrayList.add(new Data("https://eg.udacity.com/course/firebase-in-a-weekend-by-google-android--ud0352", R.drawable.dataandroid8, "Firebase in a Weekend", "In this course, you’ll learn how to use Firebase. Firebase is app development platform that provides developers a variety of tools and a scalable infrastructure to build high quality apps"));
        arrayList.add(new Data("https://www.udemy.com/become-an-android-developer-from-scratch/", R.drawable.udemyandroid, "Become an Android Developer from Scratch", "In this course, you’ll Create your own professional quality Android apps ... Deploy to the Google Play store ... Interview for a job anywhere in the world"));
        arrayList.add(new Data("https://www.edx.org/course/android-developer-capstone-project-galileox-caad005x-0", R.drawable.androidedx13, "Android Developer Capstone Project: Building a Successful Android App", "Listing the services involved with the Android Application Architecture (ex. Activity Manager, Views, Notification Manager, Content Providers, Resources Manager,etc.);\n" + "Identifying different techniques to plan, design and prototype your mobile apps before writing any code;\n" + "Applying material design principles to build compelling, beautiful interfaces for your Android apps;\n" + "Building layouts using XML and using Java code.\n" + "Using various views (e.g., buttons, text boxes and check boxes) and implement menu-based, drawer navigation or interface layouts.\n" + "Implement menu\u00AD based or drawer navigation. Constructing options menus for action bar navigation.\n" + "Determining appropriate use cases for local persisted data, and designing solutions to implement data storage using files, preferences, and databases;\n" + "Creating applications using the new version of Firebase;\n" + "Understanding the use of Model-View-Presenter (MVP);\n" + "Evidencing integrated knowledge with third-party API (Twitter,Facebook);\n"));
        arrayList.add(new Data("https://www.edx.org/course/professional-android-app-development-galileox-caad003x", R.drawable.android14, "Professional Android App Development", "What you'll learn\n" + "Firebase and Android\n" + "Model-View-Presenter (MVP)\n" + "Clean Architecture Android\n" + "Create and display a notification to the user\n" + "Building at least 5 android applications\n" + "#01 App: Building a Basic Chat Application\n" + "#02 App: Create a Twitter App\n" + "#03 App: Integrating Facebook with my App: Facebook Recipes\n" + "#04 App: Your social network of photographs!\n" + "#05 App: Building a note-taking app for android\n"));
        arrayList.add(new Data("https://www.edx.org/course/introduction-mobile-application-hkustx-comp107x-5", R.drawable.android15, "Introduction to Mobile Application Development using Android", "What you'll learn\n" + "Describe the basic components of an Android application.\n" + "Define the lifecycle methods of Android application components.\n" + "Describe the basics of event handling in Android.\n" + "Describe the basics of graphics and multimedia support in Android.\n"));
        arrayList.add(new Data("https://www.youtube.com/playlist?list=PLF8OvnCBlEY3e0Yg990aAXreEru72_xWN", R.drawable.android16, "دورة برمجة اندرويد Android Studio Tutorials Arabic", "تعليم كيفية برمجة تطبيقات الاندرويد بطريقة سهلة ومبسطة لا يحتاج المتعلم اي مستوى لبدء هذه الدورة يستطيع البدء من الصفر"));
        arrayList.add(new Data("https://www.facebook.com/groups/hendiware/", R.drawable.androidgroup, "Hendiware Developers", "جروب اندرويد(Hendiware Developers)"));

    }


    public void setArrayList‎Architecture(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;

        arrayList.add(new Data("https://www.youtube.com/playlist?list=PL31WpFF16ffieXlMDdNZe4W0XsEdEkNqT", R.drawable.ar2, "تعلم أوتوكاد", "تعلم أوتوكاد -2016-2017-2015 (كورس تدريبي للمبتدئين بالأمثلة التطبيقية)\n"));
        arrayList.add(new Data("https://www.youtube.com/playlist?list=PLk8YNqoavKIRc_a6gR1K5idMVCquwsxOn", R.drawable.ar1, "كورس اظهار", "تعلم اظهار"));
        arrayList.add(new Data("https://www.youtube.com/playlist?list=PLk1CEhTBHbIbIghuo8kjFYbQcWaVsJJij", R.drawable.ar3, "3Ds Max Course", "3Ds Max Course (Modeling)"));
        arrayList.add(new Data("https://www.edx.org/course/smart-cities-ethx-ethx-fc-03x-1", R.drawable.arch1, "Smart Cities", "By the end of the course you will be able to:\n" + "Understand the concept of smart cities, why these are still prototypes and what the challenges are.\n" + "Identify the principle of stocks and flows of information in cities at different scales.\n" + "Understand the concept of citizen design science and its importance for responsive cities.\n" + "Understand the concept of complexity science in the context of architecture and city planning.\n" + "Learn through citizen design science how you can interact and get involved in the planning of your own cities.\n" + "Articulate what a responsive city is, and identify the criteria for a city to be responsive.\n"));
        arrayList.add(new Data("https://www.edx.org/course/architectural-imagination-harvardx-gsd1x", R.drawable.arch2, "The Architectural Imagination", "How to read, analyze, and understand different forms of architectural representation\n" + "Social and historical contexts behind major works of architecture\n" + "Basic principles to produce your own architectural drawings and models\n" + "Pertinent content for academic study or a professional career as an architect\n"));
        arrayList.add(new Data("https://www.edx.org/course/urban-design-public-good-dutch-urbanism-delftx-urbanismx-0", R.drawable.arch3, "Urban Design for the Public Good: Dutch Urbanism", "What you'll learn\n" + "Dutch Urbanism basics;\n" + "How to draw simple maps of your own living environment and perform basic urban analyses of them;\n" + "How to apply your findings in small design activities;\n" + "Where to find additional literature that will deepen your knowledge and bring you up to date on the latest research findings in urban planning.\n"));
        arrayList.add(new Data("https://www.edx.org/course/responsive-cities", R.drawable.arch4, "Responsive Cities", "What you'll learn\n" + "Identify the differences between Responsive Cities and Smart Cities\n" + "Understand the concept of Citizen Design Science and its importance for Responsive Cities\n" + "Learn and use the Qua-Kit online 3D modeller for a specific urban planning task\n" + "Improve livability, governance and city planning with Responsive Cities principles\n" + "Assess and develop the quality of urban public spaces based on data and information\n"));
        arrayList.add(new Data(" https://www.youtube.com/channel/UCSBcrjaZlY_e6vBIGe4XksQ/videos", R.drawable.arch55, "Learning Autodesk 3ds Max", "3ds Max tutorialls"));

    }

    public void setArrayListBiology(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/intro-to-biology", R.drawable.bio1, "Intro to biology", "About this unit\n" + "New to biology? You're in the right place! Start your biological journey here."));
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/biology-of-viruses", R.drawable.bio2, "Viruses", "About this unit\n" + "Viruses: Are they alive? Are they dead? Are they...undead? Learn more about these fascinating (and clinically important) particles that occupy a \"gray area\" between living and non-living things."));
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/structure-of-a-cell", R.drawable.bio3, "Structure of a cell", "About this unit\n" + "You, my friend, are made up of cells. Lots and lots of them. Some of them are eukaryotic (human), but many more of them are prokaryotic, thanks to the friendly bacteria of your gut, skin, and other body systems. Jump in to learn more about prokaryotic and eukaryotic cells and the complex and beautiful structures inside of them.\n"));
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/dna-as-the-genetic-material", R.drawable.bio4, "DNA as the genetic material", "About this unit\n" + "What exactly is DNA? This seemingly simple polymer, made up of just four different types of monomers, serves as the genetic material for all living organisms. For example, your DNA provides instructions for building and maintaining your unique body and can be passed on if you have children. Learn more about the discovery, structure, and synthesis of this remarkable molecule.\n"));
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/gene-expression-central-dogma", R.drawable.bio55, "Central dogma (DNA to RNA to protein)", "About this unit\n" + "How does a gene in your DNA provide instructions for building a protein? In gene expression, a DNA sequence is first copied to make an RNA molecule, which is then \"decoded\" to build a protein. Learn more about this remarkable process, shared by all living things."));
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/gene-regulation", R.drawable.bio5, "Gene regulation", "About this unit\n" + "You have tens of thousands of genes in your genome. Does that mean your cells express all of those genes, all the time? Not by a long shot! Even an organism as simple as a bacterium must carefully regulate gene expression, ensuring that the right genes are expressed at the right time. Learn more about the mechanisms cells use to turn genes \"on\" and off"));
        arrayList.add(new Data("https://www.khanacademy.org/science/biology/human-biology", R.drawable.bio7, "Human biology", "About this unit\n" + "Your body is an amazing system! The human body is made up of groups of organs, called organ systems, that work together to keep the body in balance. In this section, we'll travel from the circulatory system, to the nervous system, to the immune system and beyond. Learn about the amazing biology that keeps your body ticking!"));
    }

    public void setArrayListDesign(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
        arrayList.add(new Data("https://www.udemy.com/photoshop-for-web-design-beginners/", R.drawable.photo1, "Photoshop for Web Design Beginners", "Photoshop is one of the best tools, so learn it with this Photoshop Web Design Tutorial!"));
        arrayList.add(new Data("https://www.udemy.com/adobe-illustrator-cc-2018-new-features/", R.drawable.photo2, "Adobe Illustrator CC 2018 - New Features", "we'll look at the small upgrades Adobe has released in Adobe Illustrator CC 2018"));
        arrayList.add(new Data("https://www.udemy.com/photoshop-fundamentals-in-one-hour/", R.drawable.photo3, "Photoshop Fundamentals", "If you’ve ever wanted to try digital painting, but you’re intimidated by photoshop, this course is for you!"));
        arrayList.add(new Data("https://www.udemy.com/how-to-use-after-effects-templates/", R.drawable.photo5, "Adobe After Effects Templates for Beginners", "A step-by-step video guide to working with After Effects templates\n" + "A free video titles template to use in this course and for your own projects\n" + "Bonus tutorials that show how to edit a variety of templates including slideshows\n" + "A teacher that is always available to help! Just send me a message!"));
        arrayList.add(new Data("https://www.udemy.com/basic-animation-in-after-effects/", R.drawable.photo6, "Basic Animation In After Effects", "you will learn the basics of animation, motion graphics, and how to apply special effects to videos"));
        arrayList.add(new Data("https://www.edx.org/course/video-game-design-balance-ritx-game102x-0", R.drawable.photo66, "Video Game Design and Balance", "you'll learn\n" + "Formal elements of games like goals and mechanics\n" + "How designers determine the appropriate audience and player group for a game\n" + "What makes different game genres different\n" + "How game designers balance games"));
        arrayList.add(new Data("https://www.edx.org/course/user-interface-ui-personalization-mitx-hdm1-2ax", R.drawable.photo9, "User Interface (UI) Personalization", "you'll learn\n" + "The difference between adaptable and adaptive user interfaces\n" + "Common approaches to user interface personalisation\n" + "•\tThe features of web pages and web applications that can be adapted to support diverse needs, especially the needs of people with disabilities"));
        arrayList.add(new Data("https://www.facebook.com/groups/grafix.design/", R.drawable.designgroup, "Graphic Designers", "Graphic Designers جروب"));
    }

    public void setArrayListPhiscal(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;

        arrayList.add(new Data("https://www.edx.org/course/super-earths-life-harvardx-spu30x-3", R.drawable.ph1, "Super-Earths And Life", "you'll learn\n" + "How life may have arisen on Earth\n" + "How we discover planets around other stars\n" + "What makes a planet favorable for life\n" + "How we search for life in our universe"));
        arrayList.add(new Data("https://www.edx.org/course/solar-energy-photovoltaic-pv-energy-delftx-pv1x-0", R.drawable.ph2, "Solar Energy: Photovoltaic (PV) Energy Conversion", "you'll learn\n" + "The principles behind the potential loss mechanisms in photovoltaic devices\n" + "The semiconductor physics necessary to understand solar cell performance and engineering\n" + "The optics and light management tools necessary for optimal solar cell design"));
        arrayList.add(new Data("https://www.edx.org/course/electricity-magnetism-part-1-ricex-phys102-1x-1", R.drawable.ph3, "Electricity & Magnetism, Part 1", "Part 1 begins with electric charge in matter, the forces between charges, the electric field, Gauss’s Law, and the electric potential. Electric current and resistance are introduced, and then DC circuits are described"));
        arrayList.add(new Data("https://www.edx.org/course/quantum-mechanics-molecular-structures-utokyox-utokyo003x-1", R.drawable.ph4, "Quantum Mechanics of Molecular Structures", "you'll learn\n" + "Two major methods to determine the geometrical structure of molecules in the gas phase: molecular spectroscopy and gas electron diffraction\n" + "How to quantize various motions of molecules, extract the quantized energy levels, and determine the geometrical structure of molecules from the spectrum"));
        arrayList.add(new Data("https://www.edx.org/course/understanding-nuclear-energy-delftx-nuclear01x-0", R.drawable.ph5, "Understanding Nuclear Energy", "you'll learn\n" + "The basics of nuclear science and nuclear energy." + "The operating principles of nuclear reactors." + "The various steps in the nuclear fuel cycle." + "The differences between current and future reactors"));
        arrayList.add(new Data("https://www.edx.org/course/atoms-stars-how-physics-explains-world-mephix-mephi003x", R.drawable.ph6, "From Atoms to Stars: How Physics Explains Our World", " you'll learn\n" + "The structure and function of our universe. " + "Basic structural levels of matter in the micro world. " + "Basic structural levels of matter at a megascale. " + "Objective laws governing the physical world"));
    }

    public void setArrayListEnglish(ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
        arrayList.add(new Data("https://www.youtube.com/playlist?list=PL5isa5XjlZ5rYwjLH3Rij6PmwxCKFFyfI", R.drawable.eng1, "من اين تبدأ تعلم الانجليزيه", "من اين تبدأ تعلم الانجليزيه"));
        arrayList.add(new Data("https://www.youtube.com/playlist?list=PL5isa5XjlZ5oAB6IOiVeIA4J9KSaEieLu", R.drawable.eng2, "English Takeaway", "English Takeaway"));
        arrayList.add(new Data("https://www.youtube.com/playlist?list=PLtRoCxMPVuefqSbQZSnCPifmVEi4g9-ri", R.drawable.eng3, "سلسلة 5000 كلمة إنجليزية", "سلسلة 5000 كلمة إنجليزية (متجددة) - بطريقة لم يسبق لها مثيل"));
    }

    public interface recyclerListener {
        void onNamClicked(int pos);
    }

    public void setOnRecyclerListener(recyclerListener listener)

    {
        this.listener = listener;
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(context).inflate(R.layout.data_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        Data data = arrayList.get(position);
        holder.imageData.setImageResource(data.getImage());
        holder.describtion.setText(data.getDescribtion());
        holder.address.setText(data.getAddress());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class DataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        recyclerListener listener;
        ImageView imageData;
        TextView address, describtion;

        public DataHolder(View itemView, recyclerListener listener) {

            super(itemView);
            this.listener = listener;
            imageData = itemView.findViewById(R.id.image_data);
            describtion = itemView.findViewById(R.id.text_data);
            address = itemView.findViewById(R.id.address);
            describtion.setOnClickListener(this);
            imageData.setOnClickListener(this);
            address.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {

            listener.onNamClicked(getAdapterPosition());
        }
    }

}
