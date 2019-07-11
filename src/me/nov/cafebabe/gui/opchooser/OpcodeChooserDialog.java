package me.nov.cafebabe.gui.opchooser;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.objectweb.asm.tree.AbstractInsnNode;

import com.alee.laf.rootpane.WebDialog;

import me.nov.cafebabe.Cafebabe;

public class OpcodeChooserDialog extends WebDialog {
	private static final long serialVersionUID = 1L;
	private int opcode;
	private OpcodeChooserPane ocp;

	public OpcodeChooserDialog(AbstractInsnNode ain) {
		super(Cafebabe.gui, true);
		this.setRound(5);
		this.setShadeWidth(20);
		this.setShowResizeCorner(false);
		this.opcode = ain.getOpcode();
		this.initBounds();
		this.setTitle("Choose an opcode");
		this.setIconImage(Cafebabe.gui.getIconImage());
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(ocp = new OpcodeChooserPane(this, ain));
		JPanel buttons = new JPanel(new FlowLayout(4));
		this.add(buttons, BorderLayout.SOUTH);

		JButton ok = new JButton("OK");
		buttons.add(ok);
		ok.addActionListener(e -> {
			setVisible(false);
		});

		JButton cancel = new JButton("Cancel");
		buttons.add(cancel);
		cancel.addActionListener(e -> {
			opcode = ain.getOpcode(); // reset opcode
			setVisible(false);
		});
		setLocationRelativeTo(getParent());
		this.setVisible(true);

	}

	private void initBounds() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.width * 0.25);
		int height = (int) (screenSize.height * 0.5);

		setBounds(screenSize.width / 2 - width / 2, screenSize.height / 2 - height / 2, width, height);
	}

	public void setOpcode(int opcode) {
		this.opcode = opcode;
	}

	public int getOpcode() {
		return opcode;
	}

	public void refresh() {
		ocp.refresh();
	}

}