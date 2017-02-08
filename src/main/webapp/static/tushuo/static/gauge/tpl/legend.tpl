<div data-cpt-con1="seriesTabKey$$key$$" class="cpt cpt-edtpn dtm-tab1-coni" style="display: $$display$$;">
    <div class="cpt cpt-sclpn dim-sclpn" style="overflow: auto;">
        <div data-cpt-sclpn-con="" class="dtm-edtcom-pn dtm-edtpn-pn cpt-sclpn-con cpt-sclpn-con-vscl" style="position: absolute; top: 0px;">
            <div class="dtm-edtcom-row dtm-edtpn-row cpt cpt-chtcf-edtblk dtm-edtcom-pn">
                <div class="dtm-edtblk-title">仪表设置(针对$$name$$系列)</div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].clockwise=true;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">是否顺时针显示</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-sltggt dtm-edtitm-ggt">
                        <div class="cpt cpt-chkbtn">
                            <div class="cpt-chkbtn-i cpt-chkbtn-i-active" data-value-index="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].clockwise=true;" data-cate="clockwise" data-type="clockwise.true">
                                <i>
                                    <em></em>
                                </i>
                                <span>是</span>
                            </div>
                            <div class="cpt-chkbtn-i" data-value-index="1" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].clockwise=false;" data-cate="clockwise" data-type="clockwise.false">
                                <i>
                                    <em></em>
                                </i>
                                <span>否</span>
                            </div>
                        </div>
                        </div>
                    </div>
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
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].radius='75%';">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">饼图半径</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="75" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');r=val+'%';chartOpt.series[ind].radius=r;" data-type="radius"></div>
                            </div>
                            <div class="dtm-msrggt-unit">%</div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].startAngle=225;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">起始角度</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="225" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].startAngle=val;" data-type="startAngle"></div>
                            </div>
                            <div class="dtm-msrggt-unit">°</div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].endAngle=-45;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">结束角度</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="-45" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].endAngle=val;" data-type="endAngle"></div>
                            </div>
                            <div class="dtm-msrggt-unit">°</div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].min=0;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">值域范围最小值</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].min=val;" data-type="min"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].max=100;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">值域范围最大值</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="100" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].max=val;" data-type="max"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].splitNumber=10;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">分隔段数</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="10" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].splitNumber=val;" data-type="splitNumber"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisTick.show.show=true;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">刻度线是否显示</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-sltggt dtm-edtitm-ggt">
                        <div class="cpt cpt-chkbtn">
                            <div class="cpt-chkbtn-i cpt-chkbtn-i-active" data-value-index="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisTick.show=true;" data-cate="axisTick.show" data-type="axisTick.show.true">
                                <i>
                                    <em></em>
                                </i>
                                <span>是</span>
                            </div>
                            <div class="cpt-chkbtn-i" data-value-index="1" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisTick.show=false;" data-cate="axisTick.show" data-type="axisTick.show.false">
                                <i>
                                    <em></em>
                                </i>
                                <span>否</span>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].splitLine.show=true;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">分隔线是否显示</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-sltggt dtm-edtitm-ggt">
                        <div class="cpt cpt-chkbtn">
                            <div class="cpt-chkbtn-i cpt-chkbtn-i-active" data-value-index="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].splitLine.show=true;" data-cate="splitLine.show" data-type="splitLine.show.true">
                                <i>
                                    <em></em>
                                </i>
                                <span>是</span>
                            </div>
                            <div class="cpt-chkbtn-i" data-value-index="1" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].splitLine.show=false;" data-cate="splitLine.show" data-type="splitLine.show.false">
                                <i>
                                    <em></em>
                                </i>
                                <span>否</span>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].title.show=true;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">盘内标题是否显示</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-sltggt dtm-edtitm-ggt">
                        <div class="cpt cpt-chkbtn">
                            <div class="cpt-chkbtn-i cpt-chkbtn-i-active" data-value-index="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].title.show=true;" data-cate="title.show" data-type="title.show.true">
                                <i>
                                    <em></em>
                                </i>
                                <span>是</span>
                            </div>
                            <div class="cpt-chkbtn-i" data-value-index="1" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].title.show=false;" data-cate="title.show" data-type="title.show.false">
                                <i>
                                    <em></em>
                                </i>
                                <span>否</span>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].title.offsetCenter=['0%', '-40%'];">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">盘内标题位置（偏移量）</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-arrvalggt dtm-edtitm-ggt">
                        <div class="dtm-row ">
                            <span class="dtm-label">x:</span>
                            <div class="dtm-con dtm-con-swc">
                                <div class="cpt cpt-msrggt dtm-ggt" style="display: block;">
                                    <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                    <div>
                                        <input type="text" value="0" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var r=chartOpt.series[ind].title.offsetCenter;r[0]=val+'%';chartOpt.series[ind].title.offsetCenter=r;" data-type="title.offsetCenter0"></div>
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
                                        <input type="text" value="-40" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var r=chartOpt.series[ind].title.offsetCenter;r[1]=val+'%';chartOpt.series[ind].title.offsetCenter=r;" data-type="title.offsetCenter1"></div>
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
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].pointer.length='80%';">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">指针长度</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="80" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');var l=val+'%';chartOpt.series[ind].pointer.length=l;" data-type="pointer.length"></div>
                            </div>
                            <div class="dtm-msrggt-unit">%</div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].pointer.width=8;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">指针宽度</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="8" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].pointer.width=val;" data-type="pointer.width"></div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisLine.lineStyle.width=30;">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">线条宽度</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-msrggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt dtm-msrggt-tx0">
                                <div>
                                <input type="text" value="30" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisLine.lineStyle.width=val;" data-type="axisLine.lineStyle.width"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dtm-edtcom-row cpt cpt-ggt-edtitm dtm-edtitm">
                    <div class="dtm-edtitm-title dtm-edtitm-tooltip" data-dsp="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisLine.lineStyle.color=[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']];">
                        <em>
                        <i class="dtmic-used"></i>
                        </em>
                        <span class="dtm-edtitm-title-con">线条色块</span>
                    </div>
                    <div class="dtm-edtitm-con">
                        <div class="cpt cpt-txiptggt dtm-edtitm-ggt">
                            <div class="cpt cpt-txipt">
                                <div>
                                <input type="text" value="[[0.2, '#91c7ae'], [0.8, '#63869e'], [1, '#c23531']]" data-opt="var ind=module.exports.getSeriesIndex('$$key$$');chartOpt.series[ind].axisLine.lineStyle.color=val;" data-type="axisLine.lineStyle.color"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
