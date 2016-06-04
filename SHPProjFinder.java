/*
 Copyright (c) 2016, Manfred Egger
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package at.gis.egger;

import com.vividsolutions.jts.geom.Envelope;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import java.util.Arrays;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.geotools.data.FeatureSource;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.opengis.referencing.operation.MathTransform;
import org.geotools.geometry.DirectPosition2D;
import java.net.URL;
import java.io.File;
import org.geotools.data.ows.Layer;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WebMapServer;
import org.geotools.feature.FeatureCollection;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.DefaultMapContext;
import org.geotools.map.MapContext;
import org.geotools.map.MapLayer;
import org.geotools.map.WMSMapLayer;
import org.geotools.map.event.MapBoundsEvent;
import org.geotools.map.event.MapBoundsListener;
import org.geotools.map.event.MapLayerListListener;
import org.geotools.ows.ServiceException;

import org.geotools.referencing.CRS;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.JMapFrame.Tool;
import org.geotools.swing.wms.WMSLayerChooser;
import org.opengis.coverage.grid.GridCoverage;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;

public class SHPProjFinder extends javax.swing.JFrame {

    static {
        System.setProperty("org.geotools.referencing.forceXY", "true");
    }
    public File shapeToTest = null;
    public static HashMap<Integer, String> listOfProjs = null;
    public static HashMap<Double, String> distListMap = null;
    public static Object[] prjList = null;
    public static double[] incoord = null;
    public static double[] refCoord = null;

    public SHPProjFinder() throws MalformedURLException, IOException {

        distListMap = new HashMap<Double, String>();
        listOfProjs = new HashMap<Integer, String>();
        String epsg_list = "https://u.jimdo.com/www400/o/sf87ce702d2eb2f53/userlayout/js/epsg-list.js";

        URL url = new URL(epsg_list);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));
        String sum = "";
        String inputLine;
        while ((inputLine = in.readLine()) != null) //System.out.println(inputLine);
        {
            listOfProjs.put(Integer.parseInt(inputLine), "EPSG:" + inputLine);
        }
        in.close();
        prjList = listOfProjs.values().toArray();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     *
     *
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button1 = new java.awt.Button();
        choice1 = new java.awt.Choice();
        button2 = new java.awt.Button();
        textField1 = new java.awt.TextField();
        textField2 = new java.awt.TextField();
        textField3 = new java.awt.TextField();
        textField4 = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        button3 = new java.awt.Button();
        list1 = new java.awt.List();
        button4 = new java.awt.Button();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        label11 = new java.awt.Label();
        label12 = new java.awt.Label();
        jLabel3 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        textField6 = new java.awt.TextField();
        label15 = new java.awt.Label();
        label13 = new java.awt.Label();
        textField5 = new java.awt.TextField();
        label14 = new java.awt.Label();
        button5 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shapefile ProjectionFinder ");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setPreferredSize(new java.awt.Dimension(900, 525));
        setResizable(false);
        setSize(new java.awt.Dimension(900, 525));
        setType(java.awt.Window.Type.UTILITY);

        jLabel1.setText("Select location/place near the selected x/y coordinates of the Shapefile to get LON/LAT values or write LON/LAT values in the textfields below:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Shapefile ProjectionFinder");

        button1.setLabel("Find");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getGeoNames(evt);
            }
        });

        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });

        button2.setLabel("Select Shapefile");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectShapefile(evt);
            }
        });

        textField1.setText("-");
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        textField2.setText("-");
        textField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField2ActionPerformed(evt);
            }
        });

        textField3.setText("-");
        textField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField3ActionPerformed(evt);
            }
        });

        textField4.setText("-");
        textField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField4ActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label1.setText("WIDTH:");

        label2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label2.setText("X:");

        label3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label3.setText("Y:");

        label4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label4.setText("HEIGHT:");

        label5.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        label5.setText("...");

        button3.setLabel("Search possible Projections");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getProjectionList(evt);
            }
        });

        list1.setMultipleMode(true);
        list1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list1ActionPerformed(evt);
            }
        });

        button4.setLabel("Start");
        button4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeCopiesOfShapes(evt);
            }
        });

        label6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label6.setText("http://spatialreference.org");

        label7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label7.setText("Make Copies of Shapefile with .prj-File for each selected Projection:");

        label8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label8.setText("http://geotools.org/");

        label9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label9.setText("http://geonames.org");

        label11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label11.setText("This Application uses:");

        label12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label12.setText("Contact: manfred@egger-gis.at                      Web: www.egger-gis.at");

        jLabel3.setText("The list below shows the distance of the Shapefiles coordinates to the LON/LAT values above");

        panel1.setBackground(new java.awt.Color(204, 255, 255));

        textField6.setText("-");
        textField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField6ActionPerformed(evt);
            }
        });

        label15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label15.setText("LON:");

        label13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label13.setText("LAT:");

        textField5.setText("-");
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout panel1Layout = new org.jdesktop.layout.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(label15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(textField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 42, Short.MAX_VALUE)
                .add(label13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(textField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 104, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(panel1Layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(panel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(textField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(label13, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(textField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(label15, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        label14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label14.setText(" Web: www.egger-gis.at");

        button5.setLabel("X/Y FROM MAP");
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5getGeoNames(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(button3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel3))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 200, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(button1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 67, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(choice1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 355, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(panel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(label12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 190, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(layout.createSequentialGroup()
                                    .add(label11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(65, 65, 65)
                                    .add(label6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(85, 85, 85)
                                    .add(label9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(label7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 416, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createSequentialGroup()
                                    .add(60, 60, 60)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, label8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, label14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(button4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, list1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jLabel2)
                                .add(layout.createSequentialGroup()
                                    .add(button2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                    .add(label5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(label3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(label2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(textField4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(textField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(24, 24, 24)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(label4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(textField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(textField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 66, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(36, 36, 36)
                            .add(button5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1)))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(label5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(button2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(button5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(layout.createSequentialGroup()
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(textField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, label2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .add(layout.createSequentialGroup()
                                    .add(1, 1, 1)
                                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(textField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(textField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(label4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(label3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(textField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))))
                .add(20, 20, 20)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(button1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(14, 14, 14)
                        .add(choice1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(panel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(36, 36, 36)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel3)
                    .add(button3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(list1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 123, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(label7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(button4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(label11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(label6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(label9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(label12, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(label8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(label14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        label2.getAccessibleContext().setAccessibleName("x:");
        label11.getAccessibleContext().setAccessibleDescription("");
        label12.getAccessibleContext().setAccessibleName("Contact: manfred@egger-gis.at                                                                                                            ");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getGeoNames(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getGeoNames
        choice1.removeAll();

        WebService.setUserName("manfredegger"); // add your username here

        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();

        searchCriteria.setQ(jTextField1.getText());
        ToponymSearchResult searchResult = null;
        try {
            //Request to geonames...
            searchResult = WebService.search(searchCriteria);
        } catch (Exception ex) {
            Logger.getLogger(SHPProjFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
        //get List of geoNames with LON/LAT values
        for (Toponym toponym : searchResult.getToponyms()) {
            if (toponym.getName().toLowerCase().contains(jTextField1.getText().toLowerCase()) && toponym.getFeatureClass().name().toLowerCase().equals("P".toLowerCase())) {
                choice1.add(toponym.getName() + " LON: " + toponym.getLongitude() + " LAT: " + toponym.getLatitude());
            }
        }
        textField6.setText(choice1.getSelectedItem().split("LON: ")[1].split("LAT")[0].trim());
        textField5.setText(choice1.getSelectedItem().split("LAT:")[1].trim());
    }//GEN-LAST:event_getGeoNames

    private void selectShapefile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectShapefile
        try {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Shapefile", "shp"));
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setMultiSelectionEnabled(false);
            fc.setDialogTitle("Please select a Shapefile...");
            int ergebnis = 0;

            ergebnis = fc.showOpenDialog(null);

            if (ergebnis == JFileChooser.APPROVE_OPTION) {
                label5.setText("Extent and centre of Shapefile coordinates are loaded...");
                label5.setForeground(Color.red);
                shapeToTest = fc.getSelectedFile();
                Map<String, URL> map = new HashMap<String, URL>();

                map.put("url", shapeToTest.toURI().toURL());

                FileDataStore store = FileDataStoreFinder.getDataStore(shapeToTest);
                FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = store.getFeatureSource();
                //INIT Transformation
                incoord = new double[2];
                incoord[0] = 13;
                incoord[1] = 47;
                refCoord = new double[2];
                refCoord[0] = 43254;
                refCoord[1] = 32653;
                double[] coord = transform("EPSG:4236", "EPSG:32632", incoord, refCoord);

                label5.setForeground(Color.black);
                label5.setText(shapeToTest.getName());
                textField4.setText("" + ((int) featureSource.getBounds().getMinX() + ((int) featureSource.getBounds().getWidth() / 2)));
                textField3.setText("" + (int) featureSource.getBounds().getHeight());
                textField2.setText("" + ((int) featureSource.getBounds().getMinY() + ((int) featureSource.getBounds().getHeight() / 2)));
                textField1.setText("" + (int) featureSource.getBounds().getWidth());

                label5.setText(shapeToTest.getName());

            }
        } catch (Exception ex) {
            Logger.getLogger(SHPProjFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_selectShapefile

    private void textField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField3ActionPerformed

    private void textField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField4ActionPerformed

    private void list1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list1ActionPerformed

    }//GEN-LAST:event_list1ActionPerformed

    private void getProjectionList(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getProjectionList
        list1.removeAll();
        list1.add("WAIT... All possible projections are analysed.");

        String sourceCRS = "EPSG:4236";

        incoord = new double[2];
        incoord[0] = Double.parseDouble(textField6.getText());
        incoord[1] = Double.parseDouble(textField5.getText());

        refCoord = new double[2];
        refCoord[0] = Integer.parseInt(textField4.getText());
        refCoord[1] = Integer.parseInt(textField2.getText());
        double[] distList = new double[prjList.length];
        //JOptionPane.showMessageDialog(null, refCoord[0] + " " + refCoord[1]);

        for (int l = 0; l < prjList.length; l++) {

            double[] coord = transform(sourceCRS, (String) prjList[l], incoord, refCoord);

            distListMap.put(coord[2], (String) prjList[l] + " DISTVALUE: " + (int) round(coord[2], 0));
            distList[l] = coord[2];
        }

        Arrays.sort(distList);
        list1.removeAll();
        for (int l = 0; l < prjList.length; l++) {
            list1.add(distListMap.get(distList[l]));
        }


    }//GEN-LAST:event_getProjectionList

    private void makeCopiesOfShapes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeCopiesOfShapes
        //get WKT from spatialreference.com for prj-Files
        try {
            String[] itemlist = list1.getSelectedItems();
            for (String sel : itemlist) {
                String epsg = sel.split(" DIST")[0];
                epsg = epsg.split(":")[1];
                File order = new File(shapeToTest.getParent());
                File[] orderOfShape = order.listFiles();
                String basename = shapeToTest.getName().split(".shp")[0] + ".";
                for (File f : orderOfShape) {
                    if (f.isFile() && f.getName().startsWith(basename)) {
                        copyFileUsingStream(f, new File(f.getParent() + "//" + epsg + "_" + f.getName()));
                    }
                }
                String url = "http://spatialreference.org/ref/epsg/" + epsg + "/esriwkt/";
                BufferedReader input = new BufferedReader(new InputStreamReader(new URL(url).openStream(), "UTF-8"));
                Stream<String> list = input.lines();
                Optional<String> findFirst = list.findFirst();
                PrintWriter writer = new PrintWriter(shapeToTest.getParent() + "//" + epsg + "_" + shapeToTest.getName().replace(".shp", ".prj"), "UTF-8");
                writer.print(findFirst.orElse(""));
                writer.close();

            }
            JOptionPane.showMessageDialog(this, "Finished!!! You find the copies in the folder of the input Shapefile.");
        } catch (Exception ex) {
            Logger.getLogger(SHPProjFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_makeCopiesOfShapes

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void textField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField6ActionPerformed

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        textField6.setText(choice1.getSelectedItem().split("LON: ")[1].split("LAT")[0].trim());
        textField5.setText(choice1.getSelectedItem().split("LAT:")[1].trim());
    }//GEN-LAST:event_choice1ItemStateChanged

    private void button5getGeoNames(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5getGeoNames
        try {
            System.out.println(shapeToTest);
            FileDataStore store = FileDataStoreFinder.getDataStore(shapeToTest);
            FeatureSource featureSource = store.getFeatureSource();
            DefaultMapContext mapContext = new DefaultMapContext();

            JMapFrame fenMap = new JMapFrame();
          
            fenMap.setTitle(shapeToTest.getName());
            fenMap.enableTool(Tool.PAN, Tool.ZOOM, Tool.RESET);
            
           
            fenMap.setDefaultCloseOperation(JMapFrame.HIDE_ON_CLOSE);
            mapContext.addLayer(featureSource, null);
            mapContext.addMapBoundsListener(new MapBoundsListener() {

                public void mapBoundsChanged(MapBoundsEvent mbe) {
                   textField4.setText(""+ (int)mbe.getNewAreaOfInterest().centre().x);
                   textField2.setText(""+ (int)mbe.getNewAreaOfInterest().centre().y);
                   textField1.setText(""+ (int)mbe.getNewAreaOfInterest().getWidth());
                   textField3.setText(""+ (int)mbe.getNewAreaOfInterest().getHeight());
                }
            });
            // Now display the map
            fenMap.enableLayerTable(false);
            
            fenMap.setMapContext(mapContext);
            fenMap.toFront();
            fenMap.getMaximumSize();

            fenMap.setSize(500, 500);

            fenMap.setVisible(true);

            // Now display the map
        } catch (IOException ex) {
            Logger.getLogger(SHPProjFinder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_button5getGeoNames

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void textField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SHPProjFinder().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(SHPProjFinder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    // the code for this method is Licensed to Prodevelop SL
    // look at http://www.programcreek.com/java-api-examples/index.php?class=org.geotools.referencing.CRS&method=decode
    public static double[] transform(String from, String to, double[] xy, double[] refCoord) {
        try {
            if (from == null || to == null) {
                return xy;
            }

            if (from.compareToIgnoreCase("EPSG:4326") == 0) {
                // E6 support 
                if (xy[0] > 181 || xy[0] < -181) {
                    xy[0] /= 1000000;
                }
                if (xy[1] > 91 || xy[1] < -91) {
                    xy[1] /= 1000000;
                }
            }

            if (from.equals(to)) {
                return xy;
            }

            CoordinateReferenceSystem from_crs = CRS.decode(from);

            // The 'true' means: 
            // "I'm going to use the order (longitude, latitude)" 
            CoordinateReferenceSystem to_crs = CRS.decode(to, true);

            //System.out.println(to_crs.getName());
            // The 'false' means: 
            // "There will be an exception if Geotools doesn't know how to do it" 
            MathTransform transform = CRS.findMathTransform(from_crs, to_crs,
                    false);

            DirectPosition2D from_point = new DirectPosition2D(xy[0], xy[1]);
            DirectPosition2D to_point = new DirectPosition2D(0, 0);
            transform.transform(from_point, to_point);

            double dist = Math.sqrt(
                    (to_point.x - refCoord[0]) * (to_point.x - refCoord[0])
                    + (to_point.y - refCoord[1]) * (to_point.y - refCoord[1])
            );
            //return new double[]{from_point.x, from_point.y, dist};
            return new double[]{to_point.x, to_point.y, dist};

        } catch (Exception exc) {
            exc.printStackTrace();
            return null;
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private java.awt.Button button2;
    private java.awt.Button button3;
    private java.awt.Button button4;
    private java.awt.Button button5;
    private java.awt.Choice choice1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private java.awt.Label label1;
    private java.awt.Label label11;
    private java.awt.Label label12;
    private java.awt.Label label13;
    private java.awt.Label label14;
    private java.awt.Label label15;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.List list1;
    private java.awt.Panel panel1;
    private java.awt.TextField textField1;
    private java.awt.TextField textField2;
    private java.awt.TextField textField3;
    private java.awt.TextField textField4;
    private java.awt.TextField textField5;
    private java.awt.TextField textField6;
    // End of variables declaration//GEN-END:variables

}
