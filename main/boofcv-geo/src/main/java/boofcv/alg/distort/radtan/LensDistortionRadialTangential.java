/*
 * Copyright (c) 2011-2019, Peter Abeles. All Rights Reserved.
 *
 * This file is part of BoofCV (http://boofcv.org).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package boofcv.alg.distort.radtan;

import boofcv.alg.distort.LensDistortionNarrowFOV;
import boofcv.alg.distort.Transform2ThenPixel_F32;
import boofcv.alg.distort.Transform2ThenPixel_F64;
import boofcv.alg.distort.pinhole.PinholePtoN_F32;
import boofcv.alg.distort.pinhole.PinholePtoN_F64;
import boofcv.struct.calib.CameraPinholeBrown;
import boofcv.struct.distort.Point2Transform2_F32;
import boofcv.struct.distort.Point2Transform2_F64;

/**
 * Radial-Tangential lens distortion model point transforms.
 *
 * @author Peter Abeles
 */
public class LensDistortionRadialTangential implements LensDistortionNarrowFOV {

	CameraPinholeBrown p;

	public LensDistortionRadialTangential(CameraPinholeBrown p) {
		this.p = p;
	}

	@Override
	public Point2Transform2_F64 distort_F64(boolean pixelIn, boolean pixelOut) {
		if( pixelIn ) {
			Point2Transform2_F64 p_to_n = new AddRadialPtoN_F64().setK(p.fx,p.fy,p.skew,p.cx,p.cy).setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F64(p_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return p_to_n;
			}
		} else {
			AddRadialNtoN_F64 n_to_n = new AddRadialNtoN_F64().setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F64(n_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return n_to_n;
			}
		}
	}

	@Override
	public Point2Transform2_F64 undistort_F64(boolean pixelIn, boolean pixelOut) {
		if( pixelIn ) {
			Point2Transform2_F64 p_to_n = new RemoveRadialPtoN_F64().setK(p.fx,p.fy,p.skew,p.cx,p.cy).setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F64(p_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return p_to_n;
			}
		} else {
			RemoveRadialNtoN_F64 n_to_n = new RemoveRadialNtoN_F64().setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F64(n_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return n_to_n;
			}
		}
	}

	@Override
	public Point2Transform2_F32 distort_F32(boolean pixelIn, boolean pixelOut) {
		if( pixelIn ) {
			Point2Transform2_F32 p_to_n = new AddRadialPtoN_F32().setK(p.fx,p.fy,p.skew,p.cx,p.cy).setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F32(p_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return p_to_n;
			}
		} else {
			AddRadialNtoN_F32 n_to_n = new AddRadialNtoN_F32().setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F32(n_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return n_to_n;
			}
		}
	}

	@Override
	public Point2Transform2_F32 undistort_F32(boolean pixelIn, boolean pixelOut) {
		if( pixelIn ) {
			Point2Transform2_F32 p_to_n = new RemoveRadialPtoN_F32().setK(p.fx,p.fy,p.skew,p.cx,p.cy).setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F32(p_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return p_to_n;
			}
		} else {
			RemoveRadialNtoN_F32 n_to_n = new RemoveRadialNtoN_F32().setDistortion(p.radial, p.t1, p.t2);
			if( pixelOut ) {
				return new Transform2ThenPixel_F32(n_to_n).set(p.fx, p.fy, p.skew, p.cx, p.cy);
			} else {
				return n_to_n;
			}
		}
	}

	@Override
	public Point2Transform2_F32 normalized_F32() {
		PinholePtoN_F32 p2n = new PinholePtoN_F32();
		p2n.set(p.fx,p.fy,p.skew,p.cx,p.cy);
		return p2n;
	}

	@Override
	public Point2Transform2_F64 normalized_F64() {
		PinholePtoN_F64 p2n = new PinholePtoN_F64();
		p2n.set(p.fx,p.fy,p.skew,p.cx,p.cy);
		return p2n;
	}

	public CameraPinholeBrown getIntrinsic() {
		return p;
	}
}
