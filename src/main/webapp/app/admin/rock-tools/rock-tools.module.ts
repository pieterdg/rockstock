import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { RockstockSharedModule } from 'app/shared/shared.module';

import { RockToolsComponent } from './rock-tools.component';

import { rockToolsRoute } from './rock-tools.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild([rockToolsRoute])],
  declarations: [RockToolsComponent]
})
export class RockToolsModule {}
