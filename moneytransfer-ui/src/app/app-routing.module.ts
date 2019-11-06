import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BonificoPageComponent} from './pages/bonifico-page/bonifico-page.component';
import {HomePageComponent} from './pages/home-page/home-page.component';

const routes: Routes = [
  { path: 'bonifico', component: BonificoPageComponent },
  { path: 'home', component: HomePageComponent},
  { path: '**',
    redirectTo: '/bonifico',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
