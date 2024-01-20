# Colour Palette Generator

## Project Description

This application will generate a colour palette of 5 random colours, with specific 
toggles available to 
generate palettes including greyscale-only palettes or pastel palettes. 
Users will be able to see the Hex colour codes and RGB information for each colour generated.
The user will then be able to save and name these palettes for later usage, with any
used toggles also saved. Users can access, edit the name, or remove previously 
saved instances.

This application can be used by:
- **UI or Graphic Designers**: When they are searching for colour inspiration
for their next projects. This palette generators allows them to continuously generate
example palettes. When a desirable palette or colour within a palette is found,
they can save or note down the specific information.
- **Students**: When working on a presentation or poster for either a class
or an extracurricular, this palette can be utilized to gain colour inspirations.
- **Interior Designers**: Similarly, this can be utilized to develop colour themes
for a home (paint, furniture, accents).

I decided to develop this project because I often find myself
looking for easy colour palette generation to help me start a creative project.
I felt this within both my student life, working within the communications
department of the UBC CSSS, and within my career interests. Over
the last summer, I worked a UX internship which involved presentation
and product design. Having a colour palette generator would be helpful
to quickly gain inspiration that I can work on more as I design further. 

## User Stories
- As a user, I want to be able to add a palette with a name.
- As a user, I want to be able to view the list of saved palettes in my palette library.
- As a user, I want to be able to select a palette in my palette library and edit the name of the palette.
- As a user, I want to be able to view the specific-colour information of a palette within the palette library.
- As a user, I want to be able to delete any unwanted palettes from my palette library.
- As a user, I want to be able to see the total amount of palettes I currently have saved.
- As a user, I want to be able to associate and change my username for my palette library.
- As a user, I want to be able to be given the option to save my palette library to file.
- As a user, I want to be given the option to load my palette to file at the start of the application.
- As a user, I want to be given the option to load my palette library from file.

## Instructions for Grader
- You can add a palette to the library by clicking one of the generating options and completing the prompted saving and naming process.
- You can remove a palette from the library by clicking the "Remove Palette" button and completing the prompted information.
- You can locate my visual component as the splash screen on the application, it is displayed while loading the application.
- You can save the state of my application by clicking "Save Library" or saving when prompted upon exit.
- You can load the state of my application by clicking "Load Library".
- You can exit my application only by clicking the "Exit" button.

## Phase 4: Task 2
Wed Nov 29 21:35:58 PST 2023 \
Library owner name set to yes. \
Wed Nov 29 21:36:00 PST 2023 \
New Palette generated. \
Wed Nov 29 21:36:02 PST 2023 \
Added Palette to Library. \
Wed Nov 29 21:36:04 PST 2023 \
New Palette generated. \
Wed Nov 29 21:36:07 PST 2023 \
New Palette generated. \
Wed Nov 29 21:36:09 PST 2023 \
Added Palette to Library. \
Wed Nov 29 21:36:14 PST 2023 \
Remove ew from library. \
Wed Nov 29 21:36:17 PST 2023 \
Library owner name set to mich. 

## Phase 4: Task 3
The biggest point of refactoring I believe I could have done was the repetitive code in
my GUI, specifically about the various generation methods. Because of the nature of using Swing for the first
time, I was not super familiar with making helper functions for the GUI. As such, instead of having a generation
function that simply took in the variable type and generated a palette corresponding to the given type,
I created 3 separate generate functions corresponding to each button. Within that method, I would just include a call
to the generate method within the Palette class with the type given. As such, there was a lot of overlapping code
related to the generation of GUI once the palette was created in order to display the colour blocks
and prompt the user to save the palette. These components of the code do not differ between each type of palette
generated. I believe that if refactoring all generate functions into 1 is difficult due to the different buttons for each
option, at the very least I should be able to refactor the palette saving process and the block generation process (which simply
calls to the palette's colours and displays them).