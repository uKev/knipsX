package org.knipsX.view.diagrams;

/**
 * This enumeration lists all predefined camera perspective and offers a method
 * which enforces the selected camera perspective on the specified 3D view.
 * 
 * @author David Kaufman
 *
 */
public enum CyclePerspectives {
	
	XYPLANE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	},
	XZPLANE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	},
	YZPLANE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	},
	PERSPECTIVE {
		@Override
		public void enforce(JAbstract3DView view) {
			// TODO Auto-generated method stub
			
		}
	};
	
	/**
	 * Enforces the selected view configuration on the JAbstract3DView specified
	 * @param view the view you want to change
	 */
	public abstract void enforce(JAbstract3DView view);
	
	
	
	/**
	 * Saves a Reference to the currently selected CycleViews enum
	 */

	static CyclePerspectives currentView; 
}


