# frankensteiner

Frankensteiner emerged to address a common workplace challenge: updating a database using sequential script files. Recognizing the repetitive and laborious nature of this task, I decided to develop this utility as a tool to automate the process.

However, during development, I identified the opportunity to expand Frankensteiner's functionalities, as detailed below:

**Flexible Concatenation**: The system allows the concatenation of the content of files ordered according to different criteria, such as alphabetical order or modification date.

**Support for Various Formats**: Frankensteiner allows the reading of different types of files, including SQL and TXT, and the writing of processed results in specific formats.

## Stitch!

In the terminal, execute the following command:

```
gradle run --args="{/path/to/folder/containing/files} {extension} {sortingWay}"
```

![stitch](https://github.com/andre-alck/frankensteiner/assets/60830711/f168ccc5-978f-45c5-980b-80d39cd224af)
