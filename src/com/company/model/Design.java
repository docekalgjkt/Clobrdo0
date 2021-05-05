package com.company.model;

import com.company.view.GUI;

import java.awt.*;

public class Design {

    private final static int[][] deska = {
            { 0,  0,  0,  0,  9, 10, 11,  0,  0,  0,  0},
            { 0,  0,  0,  0,  8,  0, 12,  0,  0,  0,  0},
            { 0,  0,  0,  0,  7,  0, 13,  0,  0,  0,  0},
            { 0,  0,  0,  0,  6,  0, 14,  0,  0,  0,  0},
            { 1,  2,  3,  4,  5,  0, 15, 16, 17, 18, 19},
            {40,  0,  0,  0,  0,  0,  0,  0,  0,  0, 20},
            {39, 38, 37, 36, 35,  0, 25, 24, 23, 22, 21},
            { 0,  0,  0,  0, 34,  0, 26,  0,  0,  0,  0},
            { 0,  0,  0,  0, 33,  0, 27,  0,  0,  0,  0},
            { 0,  0,  0,  0, 32,  0, 28,  0,  0,  0,  0},
            { 0,  0,  0,  0, 31, 30, 29,  0,  0,  0,  0}
    };
    public static int[][] getDeska() {return deska;}

    private static Component[][] deskaButtons = {
            {null, null, null, null, null, null, null, GUI.gui.getNasazovaciTlacitka()[1], null, null, null},
            {null, null, null, null, null, GUI.gui.getCile()[1][0], null, null, GUI.gui.getPoctyFigurek()[1], null, null},
            {null, GUI.gui.getPoctyFigurek()[0], null, null, null, GUI.gui.getCile()[1][1], null, null, null, null, null},
            {GUI.gui.getNasazovaciTlacitka()[0], null, null, null, null, GUI.gui.getCile()[1][2], null, null, null, null, null},
            {null, null, null, null, null, GUI.gui.getCile()[1][3], null, null, null, null, null},
            {null, GUI.gui.getCile()[0][0], GUI.gui.getCile()[0][1], GUI.gui.getCile()[0][2], GUI.gui.getCile()[0][3], GUI.gui.getKostka(), GUI.gui.getCile()[2][3], GUI.gui.getCile()[2][2], GUI.gui.getCile()[2][1], GUI.gui.getCile()[2][0], null},
            {null, null, null, null, null, GUI.gui.getCile()[3][3], null, null, null, null, null},
            {null, null, null, null, null, GUI.gui.getCile()[3][2], null, null, null, null, GUI.gui.getNasazovaciTlacitka()[2]},
            {null, null, null, null, null, GUI.gui.getCile()[3][1], null, null, null, GUI.gui.getPoctyFigurek()[2], null},
            {null, null, GUI.gui.getPoctyFigurek()[3], null, null, GUI.gui.getCile()[3][0], null, null, null, null, null},
            {null, null, null, GUI.gui.getNasazovaciTlacitka()[3], null, null, null, null, null, null, null}
    };
    public static Component[][] getDeskaButtons() {return deskaButtons;}
}
