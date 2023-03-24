package com.olmez.myamango.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringUtilityTest {

    @Test
    void testBasic() {
        String str = "toronto.docx";

        String[] split = str.split("\\.");
        assertThat(split[0]).isEqualTo("toronto");
        assertThat(split[1]).isEqualTo("docx");

        String str2 = "    t o r o n to. d   ocx    ";
        String trim = str2.replaceAll("\\s+", "");
        assertThat(trim).isEqualTo("toronto.docx");
    }

}
