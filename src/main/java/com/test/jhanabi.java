package com.test;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.Random;

public class jhanabi extends Applet implements Runnable {
    private int m_nAppX;
    private int m_nAppY;
    private int m_centerX;
    private int m_centerY;
    private int m_mouseX = 0;
    private int m_mouseY = 0;
    private int m_sleepTime = 5;
    private boolean isError = false;
    private boolean m_isPaintFinished;
    boolean isRunning;
    boolean isInitialized = false;
    Thread runner;
    int[] pix0;
    MemoryImageSource offImage;
    Image dbImg;
    int pixls;
    int pixls2;
    Random rand = new Random();
    int bits = 10000;
    double[] bit_px;
    double[] bit_py;
    double[] bit_vx;
    double[] bit_vy;
    int[] bit_sx;
    int[] bit_sy;
    int[] bit_l;
    int[] bit_f;
    int[] bit_p;
    int[] bit_c;
    int bit_max;
    int bit_sound;
    int ru;
    int rv;
    AudioClip sound1;
    AudioClip sound2;

    public jhanabi() {
        this.bit_px = new double[this.bits];
        this.bit_py = new double[this.bits];
        this.bit_vx = new double[this.bits];
        this.bit_vy = new double[this.bits];
        this.bit_sx = new int[this.bits];
        this.bit_sy = new int[this.bits];
        this.bit_l = new int[this.bits];
        this.bit_f = new int[this.bits];
        this.bit_p = new int[this.bits];
        this.bit_c = new int[this.bits];
        this.ru = 50;
        this.rv = 50;
    }

    public void init() {
        String var1 = this.getParameter("para_bits");
        if (var1 != null) {
            this.bits = Integer.parseInt(var1);
        }

        var1 = this.getParameter("para_max");
        if (var1 != null) {
            this.bit_max = Integer.parseInt(var1);
        }

        var1 = this.getParameter("para_blendx");
        if (var1 != null) {
            this.ru = Integer.parseInt(var1);
        }

        var1 = this.getParameter("para_blendy");
        if (var1 != null) {
            this.rv = Integer.parseInt(var1);
        }

        var1 = this.getParameter("para_sound");
        if (var1 != null) {
            this.bit_sound = Integer.parseInt(var1);
        }

        this.m_nAppX = this.size().width;
        this.m_nAppY = this.size().height;
        this.m_centerX = this.m_nAppX / 2;
        this.m_centerY = this.m_nAppY / 2;
        this.m_mouseX = this.m_centerX;
        this.m_mouseY = this.m_centerY;
        this.resize(this.m_nAppX, this.m_nAppY);
        this.pixls = this.m_nAppX * this.m_nAppY;
        this.pixls2 = this.pixls - this.m_nAppX * 2;
        this.pix0 = new int[this.pixls];
        this.offImage = new MemoryImageSource(this.m_nAppX, this.m_nAppY, this.pix0, 0, this.m_nAppX);
        this.offImage.setAnimated(true);
        this.dbImg = this.createImage(this.offImage);

        for(int var2 = 0; var2 < this.pixls; ++var2) {
            this.pix0[var2] = -16777216;
        }

        this.sound1 = this.getAudioClip(this.getDocumentBase(), "firework.au");
        this.sound2 = this.getAudioClip(this.getDocumentBase(), "syu.au");

        for(int var3 = 0; var3 < this.bits; ++var3) {
            this.bit_f[var3] = 0;
        }

        this.isInitialized = true;
        this.start();
    }

    public void run() {
        while(!this.isInitialized) {
            try {
                Thread.sleep(200L);
            } catch (InterruptedException var17) {
                ;
            }
        }

        while(true) {
            for(int var2 = 0; var2 < this.pixls2; ++var2) {
                int var3 = this.pix0[var2];
                int var4 = this.pix0[var2 + 1];
                int var5 = this.pix0[var2 + this.m_nAppX];
                int var6 = this.pix0[var2 + this.m_nAppX + 1];
                int var1 = (var3 & 16711680) >> 16;
                int var7 = ((((var4 & 16711680) >> 16) - var1) * this.ru >> 8) + var1;
                var1 = (var3 & '\uff00') >> 8;
                int var8 = ((((var4 & '\uff00') >> 8) - var1) * this.ru >> 8) + var1;
                var1 = var3 & 255;
                int var9 = (((var4 & 255) - var1) * this.ru >> 8) + var1;
                var1 = (var5 & 16711680) >> 16;
                int var10 = ((((var6 & 16711680) >> 16) - var1) * this.ru >> 8) + var1;
                var1 = (var5 & '\uff00') >> 8;
                int var11 = ((((var6 & '\uff00') >> 8) - var1) * this.ru >> 8) + var1;
                var1 = var5 & 255;
                int var12 = (((var6 & 255) - var1) * this.ru >> 8) + var1;
                int var13 = ((var10 - var7) * this.rv >> 8) + var7;
                int var14 = ((var11 - var8) * this.rv >> 8) + var8;
                int var15 = ((var12 - var9) * this.rv >> 8) + var9;
                this.pix0[var2] = var13 << 16 | var14 << 8 | var15 | -16777216;
            }

            this.rend();
            this.offImage.newPixels(0, 0, this.m_nAppX, this.m_nAppY);

            try {
                Thread.sleep((long)this.m_sleepTime);
            } catch (InterruptedException var16) {
                ;
            }
        }
    }

    public void update(Graphics var1) {
        this.paint(var1);
    }

    public void paint(Graphics var1) {
        var1.drawImage(this.dbImg, 0, 0, this);
    }

    public void start() {
        if (!this.isError) {
            this.isRunning = true;
            if (this.runner == null) {
                this.runner = new Thread(this);
                this.runner.start();
            }

        }
    }

    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }

    }

    public boolean mouseMove(Event var1, int var2, int var3) {
        this.m_mouseX = var2;
        this.m_mouseY = var3;
        return true;
    }

    public boolean mouseDown(Event var1, int var2, int var3) {
        this.m_mouseX = var2;
        this.m_mouseY = var3;
        int var8 = (int)(this.rand.nextDouble() * 256.0D);
        int var9 = (int)(this.rand.nextDouble() * 256.0D);
        int var10 = (int)(this.rand.nextDouble() * 256.0D);
        int var11 = var8 << 16 | var9 << 8 | var10 | -16777216;
        int var12 = 0;

        for(int var13 = 0; var13 < this.bits; ++var13) {
            if (this.bit_f[var13] == 0) {
                this.bit_px[var13] = (double)this.m_mouseX;
                this.bit_py[var13] = (double)this.m_mouseY;
                double var4 = this.rand.nextDouble() * 6.28D;
                double var6 = this.rand.nextDouble();
                this.bit_vx[var13] = Math.sin(var4) * var6;
                this.bit_vy[var13] = Math.cos(var4) * var6;
                this.bit_l[var13] = (int)(this.rand.nextDouble() * 100.0D) + 100;
                this.bit_p[var13] = (int)(this.rand.nextDouble() * 3.0D);
                this.bit_c[var13] = var11;
                this.bit_sx[var13] = this.m_mouseX;
                this.bit_sy[var13] = this.m_nAppY - 5;
                this.bit_f[var13] = 2;
                ++var12;
                if (var12 == this.bit_max) {
                    break;
                }
            }
        }

        if (this.bit_sound > 1) {
            this.sound2.play();
        }

        return true;
    }

    public boolean mouseExit(Event var1, int var2, int var3) {
        this.m_mouseX = var2;
        this.m_mouseY = var3;
        return true;
    }

    void rend() {
        boolean var1 = false;
        boolean var2 = false;
        boolean var3 = false;

        for(int var4 = 0; var4 < this.bits; ++var4) {
            switch(this.bit_f[var4]) {
                case 1:
                    this.bit_vy[var4] += this.rand.nextDouble() / 50.0D;
                    this.bit_px[var4] += this.bit_vx[var4];
                    this.bit_py[var4] += this.bit_vy[var4];
                    --this.bit_l[var4];
                    if (this.bit_l[var4] != 0 && this.bit_px[var4] >= 0.0D && this.bit_py[var4] >= 0.0D && this.bit_px[var4] <= (double)this.m_nAppX && this.bit_py[var4] <= (double)(this.m_nAppY - 3)) {
                        if (this.bit_p[var4] == 0) {
                            if ((int)(this.rand.nextDouble() * 2.0D) == 0) {
                                this.bit_set((int)this.bit_px[var4], (int)this.bit_py[var4], -1);
                            }
                        } else {
                            this.bit_set((int)this.bit_px[var4], (int)this.bit_py[var4], this.bit_c[var4]);
                        }
                        break;
                    }

                    this.bit_c[var4] = -16777216;
                    this.bit_f[var4] = 0;
                    break;
                case 2:
                    this.bit_sy[var4] -= 5;
                    if ((double)this.bit_sy[var4] <= this.bit_py[var4]) {
                        this.bit_f[var4] = 1;
                        var3 = true;
                    }

                    if ((int)(this.rand.nextDouble() * 20.0D) == 0) {
                        int var5 = (int)(this.rand.nextDouble() * 2.0D);
                        int var6 = (int)(this.rand.nextDouble() * 5.0D);
                        this.bit_set(this.bit_sx[var4] + var5, this.bit_sy[var4] + var6, -1);
                    }
            }
        }

        if (var3 && this.bit_sound > 0) {
            this.sound1.play();
        }

    }

    void bit_set(int var1, int var2, int var3) {
        int var4 = var1 + var2 * this.m_nAppX;
        this.pix0[var4] = var3;
    }
}
