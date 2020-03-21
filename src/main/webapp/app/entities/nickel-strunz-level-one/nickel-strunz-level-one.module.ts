import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { NickelStrunzLevelOneComponent } from './nickel-strunz-level-one.component';
import { NickelStrunzLevelOneDetailComponent } from './nickel-strunz-level-one-detail.component';
import { NickelStrunzLevelOneUpdateComponent } from './nickel-strunz-level-one-update.component';
import { NickelStrunzLevelOneDeleteDialogComponent } from './nickel-strunz-level-one-delete-dialog.component';
import { nickelStrunzLevelOneRoute } from './nickel-strunz-level-one.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(nickelStrunzLevelOneRoute)],
  declarations: [
    NickelStrunzLevelOneComponent,
    NickelStrunzLevelOneDetailComponent,
    NickelStrunzLevelOneUpdateComponent,
    NickelStrunzLevelOneDeleteDialogComponent
  ],
  entryComponents: [NickelStrunzLevelOneDeleteDialogComponent]
})
export class RockstockNickelStrunzLevelOneModule {}
