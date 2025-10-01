import java.util.*;

interface Text { String format(); }
class PlainText implements Text {
    private String content; public PlainText(String content){this.content=content;} 
    @Override public String format(){ return content; }
}
abstract class TextDecorator implements Text {
    protected Text decoratedText; public TextDecorator(Text decoratedText){this.decoratedText=decoratedText;}
}
class BoldText extends TextDecorator {
    public BoldText(Text decoratedText){super(decoratedText);}
    @Override public String format(){ return "**" + decoratedText.format() + "**"; }
}
class ItalicText extends TextDecorator {
    public ItalicText(Text decoratedText){super(decoratedText);}
    @Override public String format(){ return "_" + decoratedText.format() + "_"; }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Bold? (y/n): ");
        boolean bold = sc.nextLine().equalsIgnoreCase("y");

        System.out.print("Italic? (y/n): ");
        boolean italic = sc.nextLine().equalsIgnoreCase("y");

        Text myText = new PlainText(text);
        if(bold) myText = new BoldText(myText);
        if(italic) myText = new ItalicText(myText);

        System.out.println("Formatted text: " + myText.format());
        sc.close();
    }
}
