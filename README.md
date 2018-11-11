# Project functionally finished / legacy code

# Tennis-Game

![Image](https://zagorskidev.files.wordpress.com/2017/10/zrzut-ekranu-z-2017-10-05-08-36-282.png?w=1462)

# How is it done?

Build as PApplet (Processing 3.0). Player raquet follows mouse X, computer raquet follows balls X (it’s capped – always slower than ball speeded-up with attack, so player is able to win the point). Ball is moved by action listener connected to Swing timer. Balls shift multiplication is related to difficulty level. There are some good and bad power-ups that can be catched by the player during game.

# How does it look like?

You can watch short video of application running.

[![Video](https://img.youtube.com/vi/_QYMymtdfe4/0.jpg)](https://youtu.be/_QYMymtdfe4)

# How can I run it?

You can download runnable JAR here: [runnable JAR](https://drive.google.com/open?id=0B_bwkWjLwn2MQ0hwWUQ1WUZndlk).

It requires Java 8 (PApplet doesn’t work with Java 9 yet).

# How can it be improved?

Code could be refactored using some design patterns. There are some code blocks that should be splitted to helper methods. Performance could be improved by using some more efficient algorithms. Moving ball mechanics could be refactored – there are some bugs when ball is in corners on higher speeds. For now I think Processing isn’t best choice as library used to make such application, especially considering it’s problems with Java 9.
