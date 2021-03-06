/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @bug 8134329
 * @summary TeeOpTest.java fails across platforms after fix for JDK-8129547
 */
import java.util.function.Supplier;

public class T8134329 {

    static void assertEquals(Object o1, Object o2) {
        if (!o1.equals(o2)) {
            throw new AssertionError(String.format("Expected %s - found %s", o2, o1));
        }
    }

    public static void main(String[] args) {
        Supplier<String> s1 = new T8134329() { }::m;
        assertEquals(s1.get(), "m");
        Supplier<String> s2 = new T8134329() { }::g;
        assertEquals(s2.get(), "g");
        Supplier<String> s3 = new T8134329() { }::m;
        assertEquals(s3.get(), "m");
    }

    String m() { return "m"; }
    String g() { return "g"; }
}
