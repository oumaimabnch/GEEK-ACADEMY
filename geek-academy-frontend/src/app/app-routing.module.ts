import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './core/components/login/login.component';
import { NotfoundComponent } from './core/components/notfound/notfound.component';
import { RegisterComponent } from './core/components/register/register.component';
import { AuthGuard } from './core/guards/auth.guard';
import { HomeComponent } from './home/home.component';
import { ListPostComponent } from './shared/components/list-post/list-post.component';
import { PollEditorComponent } from './shared/components/course-creator/course-creator.component';
import { PostDetailsComponent } from './shared/components/post-details/post-details.component';
import { PostEditorComponent } from './shared/components/post-editor/post-editor.component';

const routes: Routes = [
  { path: '', component: ListPostComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'posts',
    children: [
      {
        path: '',
        component: ListPostComponent,
        canActivate: [AuthGuard],
      },
      {
        path: ':id',
        component: PostDetailsComponent,
        canActivate: [AuthGuard],
      },
    ],
  },

  {
    path: 'new-post',
    component: PostEditorComponent,
    canActivate: [AuthGuard],
  },
  { path: '**', component: NotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
