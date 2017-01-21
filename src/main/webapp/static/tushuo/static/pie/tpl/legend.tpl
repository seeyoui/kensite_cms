<div data-cpt-con1="seriesTabKey$$key$$" class="cpt cpt-edtpn dtm-tab1-coni" style="display: $$display$$;">
    <div class="cpt cpt-sclpn dim-sclpn">
        <div data-cpt-sclpn-con="" class="dtm-edtcom-pn dtm-edtpn-pn cpt-sclpn-con cpt-sclpn-con-vscl" style="position: absolute; top: 0px;">
            <div class="dtm-edtcom-row dtm-edtpn-row cpt cpt-chtcf-edtblk dtm-edtcom-pn">
                <div class="dtm-edtblk-title">饼图设置(针对$$name$$系列)</div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].roseType=false;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">南丁格尔玫瑰图模式</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-sltggt dtm-edtitm-ggt">
                        <div class="cpt cpt-chkbtn">
                            <div class="cpt-chkbtn-i" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].roseType='radius';" data-value-index="0" data-cate="roseType" data-type="roseType.radius">
                            <i>
                                <em></em>
                            </i>
                            <span>半径模式</span></div>
                            <div class="cpt-chkbtn-i" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].roseType='area';" data-value-index="1" data-cate="roseType" data-type="roseType.area">
                            <i>
                                <em></em>
                            </i>
                            <span>面积模式</span></div>
                            <div class="cpt-chkbtn-i cpt-chkbtn-i-active" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].roseType=false;" data-value-index="2" data-cate="roseType" data-type="roseType.false">
                            <i>
                                <em></em>
                            </i>
                            <span>普通模式</span></div>
                        </div>
                        </div>
                    </div>
                    <!-- <div class="dtm-edtitm-mask dtm-edtitm-tooltip"></div> -->
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].center=['50%', '50%'];">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">饼图圆心坐标</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-arrvalggt dtm-edtitm-ggt">
                        <div class="dtm-row ">
                            <span class="dtm-label">x:</span>
                            <div class="dtm-con dtm-con-swc">
                                <div class="cpt cpt-msrggt dtm-ggt" style="display: block;">
                                    <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                    <div>
                                        <input type="text" value="50" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var r=chartOpt.series[ind].center;r[0]=val+'%';chartOpt.series[ind].center=r;" data-type="center0"></div>
                                    </div>
                                    <div class="dtm-msrggt-unit">%</div>
                                </div>
                            </div>
                        </div>
                        <div class="dtm-row ">
                            <span class="dtm-label">y:</span>
                            <div class="dtm-con dtm-con-swc">
                                <div class="cpt cpt-msrggt dtm-ggt" style="display: block;">
                                    <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                    <div>
                                        <input type="text" value="50" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var r=chartOpt.series[ind].center;r[1]=val+'%';chartOpt.series[ind].center=r;" data-type="center1"></div>
                                    </div>
                                    <div class="dtm-msrggt-unit">%</div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                    <!-- <div class="dtm-edtitm-mask dtm-edtitm-tooltip"></div> -->
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].radius=['0%', '75%'];">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">饼图半径</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-arrvalggt dtm-edtitm-ggt" style="display: block;">
                            <div class="dtm-row ">
                                <span class="dtm-label">内:</span>
                                <div class="dtm-con dtm-con-swc">
                                    <div class="cpt cpt-msrggt dtm-ggt" style="display: block;">
                                        <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                        <div>
                                            <input type="text" value="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var r=chartOpt.series[ind].radius;r[0]=val+'%';chartOpt.series[ind].radius=r;" data-type="radius0"></div>
                                        </div>
                                        <div class="dtm-msrggt-unit">%</div>
                                    </div>
                                </div>
                            </div>
                            <div class="dtm-row ">
                                <span class="dtm-label">外:</span>
                                <div class="dtm-con dtm-con-swc">
                                    <div class="cpt cpt-msrggt dtm-ggt">
                                        <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                        <div>
                                            <input type="text" value="75" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var r=chartOpt.series[ind].radius;r[1]=val+'%';chartOpt.series[ind].radius=r;" data-type="radius1"></div>
                                        </div>
                                        <div class="dtm-msrggt-unit">%</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].startAngle=90;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">饼图起始角度</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="90" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].startAngle=val;" data-type="startAngle"></div>
                            </div>
                            <div class="dtm-msrggt-unit">°</div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].selectedMode=false;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">鼠标点选模式</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-sltggt dtm-edtitm-ggt">
                        <div class="cpt cpt-chkbtn">
                            <div class="cpt-chkbtn-i cpt-chkbtn-i-active" data-value-index="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].selectedMode=false;" data-cate="selectedMode" data-type="selectedMode.false">
                                <i>
                                    <em></em>
                                </i>
                                <span>不可选</span>
                            </div>
                            <div class="cpt-chkbtn-i" data-value-index="1" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].selectedMode='single';" data-cate="selectedMode" data-type="selectedMode.single">
                                <i>
                                    <em></em>
                                </i>
                                <span>单选</span>
                            </div>
                            <div class="cpt-chkbtn-i" data-value-index="2" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].selectedMode='multiple';" data-cate="selectedMode" data-type="selectedMode.multiple">
                                <i>
                                    <em></em>
                                </i>
                                <span>多选</span>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
