package commands;

import picocli.CommandLine;

@CommandLine.Command(
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Taking notes from the CLI"
)
public class RootCommand implements Runnable {


    @Override
    public void run() {
        System.out.println("We got u covered fam");
    }
}
