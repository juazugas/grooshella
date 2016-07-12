import org.crsh.cli.Command
import org.crsh.cli.Usage
import org.crsh.cli.Option

class date {
  @Usage("show the current time")
  @Command
  Object main( 
	  @Usage("the time format") @Option(names=["f","format"]) String format) {
      format = format ?: "EEE MMM d HH:mm:ss z yyyy"
      def date = new Date()
      date.format(format)
  }
}
