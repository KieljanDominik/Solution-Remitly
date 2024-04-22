public class main {

    public static  void  main(String[] args)
    {
        IAMPolicyValidator validator = new IAMPolicyValidator();
        if (args.length==1){
            if(validator.checkValidationOfFile(args[0]))
                System.out.println(validator.checkFieldResource(args[0]));
            else System.out.println("File didn't pass the validation.");

        }
        else System.out.println("Valid amount of arguments");

    }
}
