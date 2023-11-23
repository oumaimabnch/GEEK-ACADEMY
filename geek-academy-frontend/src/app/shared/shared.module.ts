import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListPostComponent } from './components/list-post/list-post.component';
import { PostDetailsComponent } from './components/post-details/post-details.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { PollEditorComponent } from './components/course-creator/course-creator.component';
import { FormsModule } from '@angular/forms';
import { GravatarModule } from '@infinitycube/gravatar';
import { RouterModule } from '@angular/router';
import { MatSliderModule } from '@angular/material/slider';
import { MatCommonModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgbModule, NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbdToastGlobal } from './toasts/toast-global.component';
import { NgbdToastGlobalModule } from './toasts/toast-global.module';
import { FroalaEditorModule, FroalaViewModule } from 'angular-froala-wysiwyg';
import { PostEditorComponent } from './components/post-editor/post-editor.component';

@NgModule({
  declarations: [ListPostComponent, PostDetailsComponent, ListUserComponent,PostEditorComponent],
  imports: [

    FroalaEditorModule.forRoot(),
    FroalaViewModule.forRoot(),
    CommonModule,
    RouterModule,
    FormsModule,
    GravatarModule,
    MatCommonModule,
    MatFormFieldModule,
    NgbModule,
    NgbdToastGlobalModule,
  ],
  providers: [],
  exports: [],
})
export class SharedModule {}
