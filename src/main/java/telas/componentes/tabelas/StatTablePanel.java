package telas.componentes.tabelas;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import telas.componentes.util.FontUtils;

/**
 * Painel reutilizável que exibe uma tabela com dados estatísticos.
 * A estilização (margens, tamanho) deve ser aplicada externamente.
 */
public class StatTablePanel extends JPanel {
    private final JTable table;
    private final JScrollPane scroll;

    public StatTablePanel(Object[][] data, String[] columns) {
        super(new BorderLayout());
        setBackground(Color.WHITE);

        // Cria o modelo e a tabela
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        table = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent c = (JComponent) super.prepareRenderer(renderer, row, column);
                c.setOpaque(true);
                c.setBorder(null);
                c.setBackground(isCellSelected(row, column)
                    ? adjustBrightness(Color.WHITE, 0.9f)
                    : Color.WHITE);
                return c;
            }
        };
        table.setFont(FontUtils.interOrSans(18));
        table.setRowHeight(24);
        table.setShowGrid(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setIntercellSpacing(new Dimension(2, 2));
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setFillsViewportHeight(false);
        table.setBackground(Color.WHITE);

        // Cabeçalho
        JTableHeader header = table.getTableHeader();
        header.setFont(FontUtils.interOrSans(18));
        header.setBackground(Color.WHITE);
        header.setOpaque(true);
        header.setBorder(BorderFactory.createEmptyBorder());

        // Default renderer para alinhar valores dinamicamente
        DefaultTableCellRenderer dynRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value instanceof Number) {
                    setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    setHorizontalAlignment(SwingConstants.LEFT);
                }
                return this;
            }
        };
        table.setDefaultRenderer(Object.class, dynRenderer);

        // Copyable: Ctrl+C para copiar seleção de células
        table.setCellSelectionEnabled(true);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.setTransferHandler(new TransferHandler() {
            @Override public int getSourceActions(JComponent c) { return COPY; }
            @Override protected Transferable createTransferable(JComponent comp) {
                JTable t = (JTable) comp;
                int[] rows = t.getSelectedRows();
                int[] cols = t.getSelectedColumns();
                StringBuilder sb = new StringBuilder();
                for (int r : rows) {
                    for (int j = 0; j < cols.length; j++) {
                        Object v = t.getValueAt(r, cols[j]);
                        sb.append(v != null ? v.toString() : "");
                        if (j < cols.length - 1) sb.append('\t');
                    }
                    sb.append('\n');
                }
                return new StringSelection(sb.toString());
            }
        });
        KeyStroke copyKey = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
        table.getInputMap(JComponent.WHEN_FOCUSED)
             .put(copyKey, TransferHandler.getCopyAction().getValue(Action.NAME));
        table.getActionMap()
             .put(TransferHandler.getCopyAction().getValue(Action.NAME), TransferHandler.getCopyAction());
        table.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseMoved(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                int c = table.columnAtPoint(e.getPoint());
                table.setCursor((r >= 0 && c >= 0)
                    ? Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                    : Cursor.getDefaultCursor());
            }
        });

        // JScrollPane com UI customizada inline
        scroll = new JScrollPane(table,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        scroll.getViewport().setBackground(Color.WHITE);
        JScrollBar vsb = scroll.getVerticalScrollBar();
        vsb.setBorder(BorderFactory.createEmptyBorder());
        vsb.setPreferredSize(new Dimension(8, 0));
        vsb.setUI(new BasicScrollBarUI() {
            private final Dimension zeroDim = new Dimension();
            @Override protected JButton createDecreaseButton(int orientation) { return createZeroButton(); }
            @Override protected JButton createIncreaseButton(int orientation) { return createZeroButton(); }
            private JButton createZeroButton() {
                JButton btn = new JButton();
                btn.setPreferredSize(zeroDim);
                btn.setMinimumSize(zeroDim);
                btn.setMaximumSize(zeroDim);
                return btn;
            }
            @Override protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                g.setColor(Color.WHITE);
                g.fillRect(r.x, r.y, r.width, r.height);
            }
            @Override protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(new Color(0xDDDDDD));
                g2.fillRoundRect(r.x, r.y, r.width, r.height, r.width, r.width);
                g2.dispose();
            }
        });

        add(scroll, BorderLayout.CENTER);
    }

    private Color adjustBrightness(Color c, float factor) {
        int r = Math.min(255, (int)(c.getRed() * factor));
        int g = Math.min(255, (int)(c.getGreen() * factor));
        int b = Math.min(255, (int)(c.getBlue() * factor));
        return new Color(r, g, b);
    }

    public JTable getTable() { return table; }
    public JScrollPane getScrollPane() { return scroll; }
}
