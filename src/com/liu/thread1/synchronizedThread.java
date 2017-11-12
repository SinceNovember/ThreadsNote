package com.liu.thread1;


 /** 
 * @ClassName: synchronizedThread 
 * @author: lyd
 * @date: 2017��11��9�� ����8:46:59 
 * @describe:
 * ��ͳ�̻߳��⼼����synchronized
 * ͬ����ʱ����Ҫ����������ͬһ������
 * ͬ�������������������ֻҪ��ͬ���̶߳�ִ��ͬһ��ͬ��������ʱ�����������衣
 * ͬ�����������ǹ̶���this������Ҫ��ͬ�������е��߼�ʵ��ͬ����ʱ�򣬴�����е�������Ϊthis�� 
 * ��̬ͬ�����������Ǹú�����������ֽ����ļ����󡣸ö��������this.getClass()������ȡ��Ҳ����ʹ�� ��ǰ����.class ��ʾ��
 */
public class synchronizedThread {
	public static void main(String[] args) {
		new synchronizedThread().init();
	}
	private void init(){
		final Outputer outputer=new Outputer();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
						Thread.sleep(5);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output1("liuyudong");
				}
				
			}
		}).start();
new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try{
						Thread.sleep(5);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output2("sincenoverm");
				}
				
			}
		}).start();
	}

	 	 /** 
	 	 * @ClassName: Outputer 
	 	 * @author: lyd
	 	 * @date: 2017��11��9�� ����8:40:20 
	 	 * @describe:��һ��ͬ�����̵߳��õ���ͬһ��Outputer,����ֱ�Ӽ�һ�������󼴿ɣ�35�б�ΪOutput1
	 	 */
//	 	class Outputer{
//			private Object token="";
//			public void output1(String name){
//				synchronized(token){		//��������ֹ����߳�ͬʱ�����������ֹ�޸Ļ���
//				int len=name.length();
//				  for(int i = 0; i < len; i++) {
//		                System.out.print(name.charAt(i));
//		            }
//		            System.out.println("");   
//				}
//			}
//	 }
 	 /** 
 	 * @ClassName: Outputer 
 	 * @author: lyd
 	 * @date: 2017��11��9�� ����8:42:17 
 	 * @describe:�ڶ���,����������ͬ�ķ�����ʱ��
 	 */
// 	class Outputer{
//		public void output1(String name){
//			synchronized(this){		//��������ֹ����߳�ͬʱ�����������ֹ�޸Ļ���
//			int len=name.length();
//			  for(int i = 0; i < len; i++) {
//	                System.out.print(name.charAt(i));
//	            }
//	            System.out.println("");   
//			}
//		}
//
//			public synchronized void output2(String name){
//				int len=name.length();
//				  for(int i = 0; i < len; i++) {
//		                System.out.print(name.charAt(i));
//		            }
//		            System.out.println("");   
//				}
//			}	
	///
	  /** 
	 * @ClassName: Outputer 
	 * @author: lyd
	 * @date: 2017��11��9�� ����8:47:08 
	 * @describe:�����֣���̬�ĵ�����ʹ������.class
	 */
	static class Outputer{
		public static synchronized void output1(String name){
			int len=name.length();
			  for(int i = 0; i < len; i++) {
	                System.out.print(name.charAt(i));
	            }
	            System.out.println("");   
			}
		public  void output2(String name){
			synchronized(Outputer.class){		//��������ֹ����߳�ͬʱ�����������ֹ�޸Ļ���
			int len=name.length();
			  for(int i = 0; i < len; i++) {
	                System.out.print(name.charAt(i));
	            }
	            System.out.println("");   
			}
		}	
	 }
}
