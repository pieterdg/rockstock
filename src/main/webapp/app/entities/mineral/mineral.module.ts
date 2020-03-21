import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { MineralComponent } from './mineral.component';
import { MineralDetailComponent } from './mineral-detail.component';
import { MineralUpdateComponent } from './mineral-update.component';
import { MineralDeleteDialogComponent } from './mineral-delete-dialog.component';
import { mineralRoute } from './mineral.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(mineralRoute)],
  declarations: [MineralComponent, MineralDetailComponent, MineralUpdateComponent, MineralDeleteDialogComponent],
  entryComponents: [MineralDeleteDialogComponent]
})
export class RockstockMineralModule {}
