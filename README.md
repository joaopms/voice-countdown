# Voice Countdown

A countdown timer that speaks in English, using Google Voice. Built with Java 7.

## Requirements

- Something that runs Java
- Java
- Internet connection

## Download

You can either compile the source code yourself or [download a build from the releases page](https://github.com/joaopms/voice-countdown/releases).

## Running

If you download a build, you can just run the .jar file using the command-line. Need help? [Google](https://www.google.com) is your friend.

If you're compiling the source code yourself, I guess you know what you're doing, so you probably don't need any help.

## Usage

Once the application is running, it will ask you two questions:

- How much time do you want to count (in minutes)?
- How much time do you want between warnings (in minutes)?

**NOTE:** The time between warning should be a multiple of the total time!

If no errors showed up, press *ENTER* when you're ready to start. You should hear a voice saying *x minutes left*.

The program will create a temporary folder called *audioFiles* on the directory you're running the application, but it will get deleted when the countdown finishes.

## Other languages

Although it isn't supported, it's possible to change the language of the countdown by modifying the [line 128](https://github.com/joaopms/voice-countdown/blob/master/src/net/joaopms/voicecountdown/Main.java#L128).

### Example: Portuguese

`URL url = new URL("http://translate.google.com/translate_tts?tl=pt&q=" + encodedMessage);`

## Support

If you have any question or want to report a bug, feel free to [submit an issue](https://github.com/joaopms/voice-countdown/issues/new).

## Libraries used
1. [JLayer by JavaZoom](http://www.javazoom.net/javalayer/javalayer.html)
