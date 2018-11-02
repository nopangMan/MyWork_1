package com.fzc.lowcopyjiantalk.service.views;

import com.fzc.lowcopyjiantalk.service.Bean.Movie;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.service.views
 * 文件名：MovieView
 * 创建者：fzc
 * 创建日期：2018/5/26 15:05
 * 描述
 */

public interface MovieView extends View {
    void onSuccess(Movie movie);
    void onFailed(String message);
}
