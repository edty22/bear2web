package bear2web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class NengouCal {


	public static String convWarekiToSeireki( String warekiStr ) throws Exception {



		Locale.setDefault(new Locale("ja", "JP", "JP"));
        DateFormat format = new SimpleDateFormat("GGGGyy�NM��d��");
        Date dtTmp = format.parse(warekiStr);

        Locale.setDefault( new Locale("en","US") );
        SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");

	    return sdf.format(dtTmp);

	}



	//#### �����萔
	private Calendar seirekiCal;

	private Calendar warekiCal;


	private Date dt;


	/**
	 * �R���X�g���N�^�@�uYYYY/MM/DD�v�`���̓��t��n������
	 */
	public NengouCal(String str) throws Exception {
		strDate = str;
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
		dt = sdf.parse(strDate);

		parseDate();
	}

	/**
	 * ���t�����
	 * @throws Exception
	 */
	private void parseDate() throws Exception {

	    Locale imperialLocale = new Locale("ja", "JP", "JP");
	    warekiCal = Calendar.getInstance(imperialLocale);
	    warekiCal.setTime( dt );

	    SimpleDateFormat sdf = new  SimpleDateFormat("yyyy/MM/dd");
	    strDate = sdf.format(dt);

	    seirekiCal = Calendar.getInstance();
	    seirekiCal.setTime( dt );

	    month = Integer.toString( seirekiCal.get( Calendar.MONTH ) + 1 );
	    day = Integer.toString( seirekiCal.get( Calendar.DATE ) );
	    year = Integer.toString( seirekiCal.get( Calendar.YEAR ) );
	    waYear = Integer.toString( warekiCal.get( Calendar.YEAR ) );
	    int tmpNengou = warekiCal.get( Calendar.ERA );
	    if (tmpNengou == 1) {
	    	nengou = "����";
	    } else if (tmpNengou == 2) {
	    	nengou = "�吳";
	    } else if (tmpNengou == 3) {
	    	nengou = "���a";
	    } else if (tmpNengou == 4) {
	    	nengou = "����";
	    } else {
	    	nengou = "�s��";
	    }

	}


	/**
	 * YYYY/MM/DD �`���̓��t������
	 */
	private String strDate = null;

	/**
	 * �N���@�u�����v�u���a�v
	 */
	private String nengou = null;

	/**
	 * �a��̏ꍇ�̔N
	 */
	private String waYear = null;

	/**
	 * ����̏ꍇ�̔N
	 */
	private String year = null;

	/**
	 * ��
	 */
	private String month = null;

	/**
	 * ��
	 */
	private String day = null;


	//############# �֐��Q #########################
	/**
	 * �����ɂ��܂�
	 */
	public void nextDay() throws Exception {
		seirekiCal.add(Calendar.DATE ,1);
		dt = seirekiCal.getTime();
		warekiCal.add(Calendar.DATE ,1);
		parseDate();
	}

	/**
	 * �O���ɂ��܂�
	 */
	public void prevDate() throws Exception {
		seirekiCal.add(Calendar.DATE ,-1);
		dt = seirekiCal.getTime();
		warekiCal.add(Calendar.DATE ,-1);
		parseDate();
	}


	//############# GETTER SETTER #########################
	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getNengou() {
		return nengou;
	}

	public void setNengou(String nengou) {
		this.nengou = nengou;
	}

	public String getWaYear() {
		return waYear;
	}

	public void setWaYear(String waYear) {
		this.waYear = waYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

}
