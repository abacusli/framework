package com.lechinepay.framework;

import java.io.File;

public class SystemLib {

    public static void main(String[] args) {
        File file = new File("/media/abacusli/0DCD12EF0DCD12EF/lechinepay.channel/lepay_wxpay_multi/WebContent/WEB-INF/lib");
        for (File f : file.listFiles()) {

            System.out.println("<dependency>");
            System.out.println("<groupId>" + f.getName().substring(0, f.getName().lastIndexOf(".")) + "</groupId>");
            System.out
                    .println("<artifactId>" + f.getName().substring(0, f.getName().lastIndexOf(".")) + "</artifactId>");
            System.out.println("<version>1.0-local</version>");
            System.out.println("<scope>system</scope>");
            System.out.println("<systemPath>${basedir}/WebContent/WEB-INF/lib/" + f.getName() + "</systemPath>");
            System.out.println("</dependency>");
        }

    }
}
