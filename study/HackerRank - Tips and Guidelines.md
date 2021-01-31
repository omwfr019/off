7 Steps to Solve Algorithm Problems
=====================================

source : HackerRank <br/>
(https://www.hackerrank.com/interview/interview-preparation-kit/tips-and-guidelines/videos)


<br/>

### 1. Listen carefully to the problem
Typically every detail is needed to solve problem. <br/>
if you solve a problem without using a detail, you might need that to solve it optimally. <br/>
every single detail in the problem make sure you use it.

ex) Given to arrays (sorted + distinct), find elements in common. <br/>
    => sorted : needed to solve optimally

<br/>

### 2. Come up with a good example
Most people's examples look list this
 > A : 1, 5, 15, 20 <br/>
 > B : 2, 5, 13, 30
 
Technically if it's a problem but it's not very useful. <br/>
as soon as glance at this example you notice that there's only element common and you know exactly what it is. <br/>
because this example is 'so small' and it's actually of a 'special case'. <br/>

Better example
 > A : 1, 5, 15, 20, 30, 37 <br/>
 > B : 2, 5, 13, 30, 32, 35, 37, 42
much larget and you've avoided some special cases. <br/>

Make your examples larger and really avoid special cases.

<br/>

### 3. Come up with a brute force algorithm
Better to have a brute force than nothing at all. <br/>
(it is so much better to start off with something slow then to start off with nothing at all) <br/>

It's fine if your first algorithm is slow and terrible whatever.
Because
- check that you understand the problem
- Shows you're at least good enough to get that
- It's a good place to optimize from

To code the brute force (X)
Don't code it
- state your brute force algorithm
- state its runtime
- immediately go to optimizing

<br/>

### 4. Optimize
A good chuck of time on algorithm interview question will often be spent on optimizations.

<br/>

### 5. Walk through your algorithm
Once you have an optimal algorithm or you're ready to start coding take a step back and just make sure you know exactly what you're  going to do in your code. <br/>
Know exactly what you're going to do before coding.

- what variables & data structures?
- how, when + why do they change?
- what is the structure of your code?

<br/>

### 6. Start coding
##### Whiteboard
* try to wirte your lines straight. <br/>
  when people start writing their lines and sharp angles they start to lose track over whether this if statement under this for loop or not.
* use your board space wisely. <br/>
  erase what you don't need. wirte in top, left corner. <br/>
  if you do run out of space through, it's ok to use arrows.
##### Whiterboard or Computer
* coding style matters.
* consistent braces, consistent variable naming conventions, consistent camel case or underscores, consistent spaces.
* be consistent in your style.
* descriptive variable names are important to good style.
* compromise - write the good descriptive variable name first and then just ask your interviewer, is it okay if I abbreviate this the next time.
* <b>MODULARIZE</b> - before, not after <br/>
  + modularize your code up front and just any little conceptual chunks of code, push that off to another function. <br/>
  + suppose you have three steps in your algorithm - process the first string, process the second string, and then compare the results. <br/>
    don't start writing these for loops that walk through each string in the very beginning. Instead write this overall function that wraps these three steps. <br/>
    
    > void doSomething (a,b) { <br/>
    >  &nbsp;&nbsp;&nbsp;&nbsp; int[] r = processString(a), <br/>
    >  &nbsp;&nbsp;&nbsp;&nbsp; int[] s = processString(b), <br/>
    >  &nbsp;&nbsp;&nbsp;&nbsp; return compare Results(r,s) <br/>
    >  }
      
    so step one, step two, step three, and then start drilling in and going  into all the details there. <br/>
    any conceptual chunks of code push those off to other functions, don't wirte them in line. <br/>
    
<br/>

### 7. Testing your code
Poeple's mistakes - their big example from step 2 and throw that in as a test case. <br/>
=> it's very large so it will take you a long time to run through but also you just used that to develop your codes, so if here's an oversight there, the problem will probably repeat itself here. <br/>

1. Analyse - Think about each line <br/>
    - double check things that look weired (for loops that decrement, lath, etc, ...)
2. Use test case - Small test-cases first <br/>
    - faster to run
    - you will probably be more thorough
3. Use test case - Edge cases
4. Use test case - Big test cases

Remember
  - think as you test (don't be a bot)
  - test your code, not your algorithm
  - think before your fix bugs. don't panic. <br/>
    if you make the wrong fix to your code, the thing that just fixed the output but didn't fix a real bug you've not fixed the actual bug.

