package bear2web.util;

import java.net.*;

import bear2web.model.Price;

import java.io.*;


public class StockPriceCollector {
	
	/**
	 * Yahoo���玞�����z���擾
	 * @return
	 * @throws Exception
	 */
	public String getJikaByYahoo( String brandCode ) throws Exception {

		String jika = "";
		try {
			
			URL helloURL = 
				new URL("http://quote.yahoo.co.jp/q?s=" + brandCode + "&d=t");
			
			
			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(helloURL.openStream() ,"EUC-JP" ));
			
			// ��s���ǂݍ��݂܂�
			String line;
			while ((line = in.readLine()) != null) {
				
				//
				if ( line.indexOf("����l") > 0 && line.indexOf("�O����") > 0 ) {
					
					//��Ɩ�
					String key = "<td nowrap>&nbsp;<b>";
					String tmp = line.substring( line.indexOf( key ) );
					String tmpVal = null;
					String compName = tmp.substring(key.length(), tmp.indexOf("</b>"));
					
					//System.out.println( compName );
					
					//�}�[�P�b�g
					key = "<b>�y</b>";
					tmp = tmp.substring( tmp.indexOf(key) );
					String marketName = tmp.substring(key.length(), tmp.indexOf("�F<b>") );
					
					//System.out.println( marketName );
						
					//�،��R�[�h
					key = "�F<b>";
					tmp = tmp.substring( tmp.indexOf(key) );
					
					//System.out.println( tmp.substring(key.length(), tmp.indexOf("</b>") ));
						
					//����l
					
					tmp = tmp.substring( tmp.indexOf("����l<br>") );
					key = "<b>";
					
					//�����̊������ǂ����`�F�b�N
					String tmpChk = tmp.substring(0, tmp.indexOf( key ));
					
					//����l�̂Ƃ���ɕ\������Ă��鎞�Ԃ��uHH:MI�v�`���Ȃ��OK
					//�uYY/MM�v�`�����ƑO���ȑO�̒l�Ȃ̂Ŏ擾���Ȃ�
					if (tmpChk.indexOf(":") >= 0) {
						;
					} else {
						return null;
					}
					
					
					tmp = tmp.substring( tmp.indexOf( key ) );
					
					
					
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</b>") );
					tmpVal = tmpVal.replaceAll(",", "");
					tmpVal = tmpVal.replaceAll("\\(�ŏI�C�z\\)", "");
					tmpVal = tmpVal.replaceAll(" ", "");
					
					//�o����
					key = "�o����<br>";
					tmp = tmp.substring( tmp.indexOf( key ) );
					
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</td>"));
					tmpVal = tmpVal.replaceAll(",", "");
					
					//�������z
					key = "�������z<br>";
					tmp = tmp.substring( tmp.indexOf( key ) );
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</td>"));
					tmpVal = tmpVal.replaceAll(",", "");
					
					jika = tmpVal;
					
					//�n�l
					key = "�n�l<br>";
					tmp = tmp.substring( tmp.indexOf( key ) );
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</td>") );
					tmpVal = tmpVal.replaceAll(",", "");
					
					//price.setStartPrice( Integer.parseInt( tmpVal ) );
					
					//���l
					key = "���l<br>";
					tmp = tmp.substring( tmp.indexOf( key ) );
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</td>") );
					tmpVal = tmpVal.replaceAll(",", "");
					
					//price.setHighPrice( Integer.parseInt( tmpVal ) );
					
					//���l
					key = "���l<br>";
					tmp = tmp.substring( tmp.indexOf( key ) );
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</td>") );
					tmpVal = tmpVal.replaceAll(",", "");
					
					//price.setLowPrice( Integer.parseInt( tmpVal ) );
					
					//�P������
					key = "�P������<br>";
					tmp = tmp.substring( tmp.indexOf( key ) );
					tmpVal = tmp.substring(key.length(), tmp.indexOf("</td>") );
					tmpVal = tmpVal.replaceAll(",", "");
					tmpVal = tmpVal.replaceAll("��", "");
					
					//�P�������o�^�ł��Ȃ��`�ł���΁u1�v�ł悢
					try {
						//price.setUnit( Integer.parseInt( tmpVal ) );
					} catch (Exception e) {
						e.printStackTrace();
						
					}
					
					//System.out.println( tmpVal  );
					
				}
			}

			// ���̓X�g���[������܂�
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jika;


	}

}
