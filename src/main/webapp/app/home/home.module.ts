import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { HomeSummaryComponent } from './summary/home-summary.component';
import { HomeSummaryService } from './summary/home-summary.service';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent, HomeSummaryComponent],
  providers: [HomeSummaryService]
})
export class RockstockHomeModule {}
