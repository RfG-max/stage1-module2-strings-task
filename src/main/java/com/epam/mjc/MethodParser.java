package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String accessModifier;
        String returnType;
        String methodName;
        List<MethodSignature.Argument> arguments = new ArrayList<>();


        int index = signatureString.indexOf('(');
        String accessTypeName = signatureString.substring(0,index);
        String stringArguments = signatureString.substring(index+1);
        String[] atn = accessTypeName.split(" ");
        if (atn.length == 2)  {
            accessModifier = null;
            returnType = atn[0];
            methodName = atn[1];
        }
        else {
            accessModifier = atn[0];
            returnType = atn[1];
            methodName = atn[2];
        }

        if (index == signatureString.length()-2){
            MethodSignature methodSignature = new MethodSignature(methodName, Collections.EMPTY_LIST);
            methodSignature.setAccessModifier(accessModifier);
            methodSignature.setReturnType(returnType);

            return methodSignature;
        }

        String[] args = stringArguments.split(" ");
        int i=0;
        while (i<args.length){
            MethodSignature.Argument argument = new MethodSignature.Argument(args[i],args[i+1].substring(0,args[i+1].length()-1));
            arguments.add(argument);
            i=i+2;
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);

        return methodSignature;
    }
}
