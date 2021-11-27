# Java Spring Boot Application for Student Monitoring System
> This project has a web portal from where the faculties can enable the attendance in any of the two modes,
> - Single attendance mode 
> - Continuous monitoring mode

> __Single attendance mode:__
>   This is similar to the standard roll call method , the student is checked only once.
> __Continuous attendance mode :__
>   In this mode the student will be checked in random intervals and report will be generated with the collective data.

> # Features :
>   - Faculty can view attendance in realtime.
>   - Faculty can generate report and it will be saved to database, the report can be downloaded as csv
>   - Faculty can view analysis of the class
>   - Students can view their realtime attendance percentage
>   - NoticeBoard feature is available with priority modes.

> Architecture of the System :
> ![System Architecture](sample_img/arch_img.png?raw=true "System Architecture")

> Flow diagram :
> ![Flow Diagram](sample_img/flow_img.png?raw=true "Flow  Diagram of the system")

> Database Relation diagram :
> ![Database Relation Diagram](sample_img/db_img.png?raw=true "Flow  Diagram of the system")

> How to use ?
> - Download or clone this repo
> - Open the project in Spring Tool Suite or Other IDE
> - Build the project with maven
> - To make changes to the UI edit the frontend ReactJs part and create production build and replace the files in `resources/static/`
> - Change IP address in settings.json in frontend
> - Confiure SSL if youre gonna serve on HTTPS and edit the `resources/application.properties`
> - Download the sample database sql file from sample_img folder or create your own with that as reference.
> - start the server and you're good to go.

> Got any issues ? Contact me @ simclair.sgs@gmail.com\
> sample screenshots of this project :
> ![IMG1](sample_img/img_1.png?raw=true "")
> ![IMG2](sample_img/img_2.png?raw=true "")
> ![IMG3](sample_img/img_2.png?raw=true "")
> ![IMG4](sample_img/img_2.png?raw=true "")
> ![IMG5](sample_img/img_2.png?raw=true "")
> ![IMG6](sample_img/img_6.png?raw=true "")
> ![IMG7](sample_img/img_7.png?raw=true "")


> - Thanks for using...
> - With regards ,
> - @author George Simclair Sam [simclair.sgs@gmail.com] and Elitecoders team
