# A Simple Training Log

## Specifically for runners

As a member of UBC Varsity Athletics, there's a level of accountability expected by our coaching and strength staff. 
In order for them to optimise our training, they need to know exactly how we performed during workouts and off days.
*However*, despite being very active individuals, athletes are very lazy when it comes to filling in their training 
logs. The purpose of this project is to streamline the process of getting athletes to fill in their logs. This way 
they'll actually be able to tell their coaches what they were doing in previous weeks, and be able to plan the next
few weeks effectively.

With this application you can:
- Add a running activity
- Add a strength activity
- View your previous activities
- Track shoe mileage

## User Stories
- As a user, I want to be able to create a new activity and add it to my training log
- As a user, I want to be able to add a pair of shoes to track its mileage
- As a user, I want to be able to view my previous activities
- As a user, I want to be able to view the mileage on my shoes
- As a user, I want to be able to save my activities
- As a user, I want to be able to load my training log activities from file

## Phase 4: Task 2
Implemented a type hierarchy with RunningActivity and Strength activity both implementing the Activity
abstract class. The two classes have their own unique getSummaryOfActivity function.

## Phase 4: Task 3
One thing I would do if I had more time is to create an abstract Panel and abstract Form class, as I noticed that 
classes under the forms and panels sub folders had a lot of similar functionality. I would also reformat the graphical
design of the program to look more visually pleasing.