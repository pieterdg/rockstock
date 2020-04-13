import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { RockstockSharedModule } from 'app/shared/shared.module';
import { RockstockCoreModule } from 'app/core/core.module';
import { RockstockAppRoutingModule } from './app-routing.module';
import { RockstockHomeModule } from './home/home.module';
import { RockstockEntityModule } from './entities/entity.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';
import 'hammerjs';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
        BrowserAnimationsModule,
        FlexLayoutModule,
    RockstockSharedModule,
    RockstockCoreModule,
    RockstockHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    RockstockEntityModule,
    RockstockAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class RockstockAppModule {}
