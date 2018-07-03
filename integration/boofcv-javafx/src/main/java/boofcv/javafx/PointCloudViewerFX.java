/*
 * Copyright (c) 2011-2018, Peter Abeles. All Rights Reserved.
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

package boofcv.javafx;

import boofcv.struct.Point3dRgbI;
import boofcv.visualize.PointCloudViewer;
import georegression.struct.se.Se3_F64;
import javafx.application.Platform;

import javax.swing.*;
import java.util.List;

public class PointCloudViewerFX implements PointCloudViewer {

	PointCloudViewerPanelFX panel = new PointCloudViewerPanelFX();

	@Override
	public void setCloudXyzRgbI32(List<Point3dRgbI> cloud) {
		Platform.runLater(() -> panel.setCloud(cloud));
	}

	@Override
	public void setCameraFov(double radians) {

	}

	@Override
	public void setCameraToWorld(Se3_F64 cameraToWorld) {

	}

	@Override
	public JComponent getComponent() {
		return panel;
	}
}
