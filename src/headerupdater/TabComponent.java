/*
 * The MIT License
 *
 * Copyright 2018 Carl Sampson <chs@chs.us>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package headerupdater;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class TabComponent extends JPanel
{

    IBurpExtenderCallbacks callbacks;
    BurpExtender extender;
    final private JCheckBox enabled;
    final private JCheckBox debug;
    final private JTextArea headerStart;
    final private JLabel headerLabel;
    final private JLabel headerStartLabel;
    final private JLabel headerReplaceLabel;
    final private JTextArea headerReplace;

    public TabComponent(IBurpExtenderCallbacks callbacks, BurpExtender extender)
    {
        this.callbacks = callbacks;
        this.extender = extender;

        this.headerLabel = new JLabel();
        this.enabled = new JCheckBox();
        this.debug = new JCheckBox();
        this.headerStart = new JTextArea(5,40);
        this.headerStartLabel = new JLabel();
        this.headerReplaceLabel = new JLabel();
        this.headerReplace = new JTextArea(5,40);
        
        initComponents();

        this.callbacks.customizeUiComponent(this.enabled);
        this.callbacks.customizeUiComponent(this.debug);
        this.callbacks.customizeUiComponent(this.headerStart);
        this.callbacks.customizeUiComponent(this.headerLabel);
        this.callbacks.customizeUiComponent(this.headerStartLabel);
        this.callbacks.customizeUiComponent(this.headerReplaceLabel);
        this.callbacks.customizeUiComponent(this.headerReplace);
    }
 
    private void initComponents()
    {        
        this.headerLabel.setFont(new Font("Tahoma", 1, 16));
        this.headerLabel.setForeground(new Color(229, 137, 0));
        this.headerLabel.setText("Header Settings");
        this.headerLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        this.enabled.setSelected(false);
        this.enabled.setText("Enabled");
        this.enabled.setAlignmentX(CENTER_ALIGNMENT);
        
        this.debug.setSelected(false);
        this.debug.setText("Debug");
        this.debug.setAlignmentX(CENTER_ALIGNMENT);

        this.headerStartLabel.setFont(new Font("Tahoma", 1, 13));
        this.headerStartLabel.setForeground(new Color(229, 137, 0));
        this.headerStartLabel.setText("Header: ");
        this.headerStartLabel.setAlignmentX(LEFT_ALIGNMENT);   
        this.headerStart.setLineWrap(true);
                
        this.headerReplaceLabel.setFont(new Font("Tahoma", 1, 13));
        this.headerReplaceLabel.setForeground(new Color(229, 137, 0));
        this.headerReplaceLabel.setText("Value: ");
        this.headerReplaceLabel.setAlignmentX(LEFT_ALIGNMENT);     
        this.headerReplace.setLineWrap(true);

        JPanel tokenPanel = new JPanel(new FlowLayout());
        tokenPanel.add(headerStartLabel);
        tokenPanel.add(headerStart);
        tokenPanel.add(headerReplaceLabel);
        tokenPanel.add(headerReplace);
        
        JPanel boxPanel = new JPanel(new FlowLayout());
        boxPanel.add(enabled);
        boxPanel.add(debug);
        
        JPanel panel = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxlayout);
        
        //Add the items
        panel.add(this.headerLabel);
        panel.add(new JLabel("  ")); 
        panel.add(tokenPanel);
        panel.add(new JLabel("  "));         
        panel.add(boxPanel);
        this.add(panel);
    }
    
    @Override
    public boolean isEnabled()
    {
        return enabled.isSelected();
    }
    
    public boolean isDebug()
    {
        return debug.isSelected();
    }
    
    public String getHeaderStart()
    {
        return headerStart.getText();
    }
    
    public String getHeaderReplace()
    {
        return headerReplace.getText();
    }
}